package de.symeda.sormas.ui.events;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.symeda.sormas.api.FacadeProvider;
import de.symeda.sormas.api.event.EventDto;
import de.symeda.sormas.api.event.TypeOfPlace;
import de.symeda.sormas.api.location.LocationDto;
import de.symeda.sormas.api.region.DistrictReferenceDto;
import de.symeda.sormas.api.user.UserReferenceDto;
import de.symeda.sormas.api.user.UserRole;
import de.symeda.sormas.api.utils.DateHelper;
import de.symeda.sormas.ui.ControllerProvider;
import de.symeda.sormas.ui.location.LocationForm;
import de.symeda.sormas.ui.login.LoginHelper;
import de.symeda.sormas.ui.utils.AbstractEditForm;
import de.symeda.sormas.ui.utils.CssStyles;
import de.symeda.sormas.ui.utils.FieldHelper;
import de.symeda.sormas.ui.utils.LayoutUtil;

@SuppressWarnings("serial")
public class EventDataForm extends AbstractEditForm<EventDto> {
	
	private static final String STATUS_CHANGE = "statusChange";
	
	private List<UserReferenceDto> assignableSurveillanceOfficers;
	
	private static final String HTML_LAYOUT = 
			LayoutUtil.h3(CssStyles.VSPACE3, "Event data") +
			LayoutUtil.divCss(CssStyles.VSPACE2,
					LayoutUtil.fluidRowCss(CssStyles.VSPACE4,
							LayoutUtil.fluidColumn(12, 0,
									LayoutUtil.fluidRowLocs(EventDto.UUID, EventDto.EVENT_TYPE, EventDto.DISEASE)
							)
					) +
					LayoutUtil.fluidRowCss(CssStyles.VSPACE4,
							LayoutUtil.fluidColumnCss(null, 4, 0,
									LayoutUtil.fluidRowLocs(EventDto.EVENT_DATE)),
							LayoutUtil.fluidColumnCss(null, 8, 0,
									LayoutUtil.fluidRowLocs(EventDto.EVENT_STATUS))
					) +
					LayoutUtil.fluidRowCss(CssStyles.VSPACE4,
							LayoutUtil.fluidColumn(12, 0, 
									LayoutUtil.fluidRowLocs(EventDto.EVENT_DESC))
					)
			) +
			LayoutUtil.h3(CssStyles.VSPACE3, "Source of information") +
			LayoutUtil.divCss(CssStyles.VSPACE2,
					LayoutUtil.fluidRowCss(CssStyles.VSPACE4, 
							LayoutUtil.fluidColumn(12, 0, 
									LayoutUtil.fluidRowLocs(EventDto.SRC_FIRST_NAME, EventDto.SRC_LAST_NAME) +
									LayoutUtil.fluidRowLocs(EventDto.SRC_TEL_NO, EventDto.SRC_EMAIL)
							)
					)
					
			) +
			LayoutUtil.h3(CssStyles.VSPACE3, "Location") +
			LayoutUtil.divCss(CssStyles.VSPACE2,
					LayoutUtil.fluidRowCss(CssStyles.VSPACE4, 
							LayoutUtil.fluidColumn(8, 0, 
									LayoutUtil.fluidRowLocs(EventDto.EVENT_LOCATION)
							),
							LayoutUtil.fluidColumn(4, 0,
									LayoutUtil.fluidRowLocs(EventDto.TYPE_OF_PLACE) +
									LayoutUtil.fluidRowLocs(EventDto.TYPE_OF_PLACE_TEXT)
							)
					)
			) +
			LayoutUtil.fluidRowCss(CssStyles.VSPACE4,
					LayoutUtil.fluidColumn(12,  0,
							LayoutUtil.fluidRowLocs(EventDto.SURVEILLANCE_OFFICER, EventDto.REPORT_DATE_TIME, EventDto.REPORTING_USER)
					)
			);
	
	private final VerticalLayout statusChangeLayout;
	
	public EventDataForm() {
		super(EventDto.class, EventDto.I18N_PREFIX);
		statusChangeLayout = new VerticalLayout();
		statusChangeLayout.setSpacing(false);
		statusChangeLayout.setMargin(false);
		getContent().addComponent(statusChangeLayout, STATUS_CHANGE);
	}

	@Override
	protected void addFields() {
		addField(EventDto.UUID, TextField.class);
		addField(EventDto.EVENT_TYPE, OptionGroup.class);
		addField(EventDto.DISEASE, ComboBox.class).setNullSelectionAllowed(true);
		addField(EventDto.EVENT_DATE, DateField.class);
		addField(EventDto.EVENT_STATUS, OptionGroup.class);
		addField(EventDto.EVENT_DESC, TextArea.class).setRows(2);
		addField(EventDto.EVENT_LOCATION, LocationForm.class).setCaption(null);

		LocationForm locationForm = (LocationForm) getFieldGroup().getField(EventDto.EVENT_LOCATION);
		Field districtField = locationForm.getFieldGroup().getField(LocationDto.DISTRICT);
		
		UserReferenceDto currentUser = LoginHelper.getCurrentUserAsReference();
		assignableSurveillanceOfficers = FacadeProvider.getUserFacade().getAssignableUsers(currentUser, UserRole.SURVEILLANCE_OFFICER);
		addField(EventDto.SURVEILLANCE_OFFICER, ComboBox.class).addItems(
				ControllerProvider.getUserController().filterByDistrict(assignableSurveillanceOfficers, (DistrictReferenceDto) districtField.getValue()));
		
		addField(EventDto.TYPE_OF_PLACE, ComboBox.class);
		addField(EventDto.TYPE_OF_PLACE_TEXT, TextField.class);		
		addField(EventDto.REPORT_DATE_TIME, DateField.class);
		addField(EventDto.REPORTING_USER, ComboBox.class);
		addField(EventDto.SRC_FIRST_NAME, TextField.class);
		addField(EventDto.SRC_LAST_NAME, TextField.class);
		addField(EventDto.SRC_TEL_NO, TextField.class);
		addField(EventDto.SRC_EMAIL, TextField.class);
		
		DateField dateTimeField = (DateField) getField(EventDto.REPORT_DATE_TIME);
		dateTimeField.setResolution(Resolution.MINUTE);
		dateTimeField.setDateFormat(DateHelper.getTimeDateFormat().toPattern());
		
		setReadOnly(true, EventDto.UUID, EventDto.REPORT_DATE_TIME, EventDto.REPORTING_USER);
		
		FieldHelper.setVisibleWhen(getFieldGroup(), EventDto.TYPE_OF_PLACE_TEXT, EventDto.TYPE_OF_PLACE, Arrays.asList(TypeOfPlace.OTHER), true);
		setTypeOfPlaceTextRequirement();
		
		setRequired(true, EventDto.EVENT_TYPE, EventDto.EVENT_DATE, EventDto.EVENT_STATUS, EventDto.UUID, EventDto.EVENT_DESC,
				EventDto.REPORT_DATE_TIME, EventDto.REPORTING_USER, EventDto.TYPE_OF_PLACE, EventDto.SRC_FIRST_NAME,
				EventDto.SRC_LAST_NAME, EventDto.SRC_TEL_NO, EventDto.TYPE_OF_PLACE_TEXT, EventDto.SURVEILLANCE_OFFICER);
		locationForm.setFieldsRequirement(true, LocationDto.REGION, LocationDto.DISTRICT);
		
		setSurveillanceOfficerListener(locationForm);
	}
	
	@Override
	protected String createHtmlLayout() {
		return HTML_LAYOUT;
	}
	
	public void setTypeOfPlaceTextRequirement() {
		FieldGroup fieldGroup = getFieldGroup();
		Field typeOfPlaceField = fieldGroup.getField(EventDto.TYPE_OF_PLACE);
		Field typeOfPlaceTextField = fieldGroup.getField(EventDto.TYPE_OF_PLACE_TEXT);
		((AbstractField) typeOfPlaceField).setImmediate(true);
		
		// initialize
		{
			typeOfPlaceTextField.setRequired(typeOfPlaceField.getValue() == TypeOfPlace.OTHER);
		}
		
		typeOfPlaceField.addValueChangeListener(event -> {
			typeOfPlaceTextField.setRequired(typeOfPlaceField.getValue() == TypeOfPlace.OTHER);
		});
	}
	
	private void setSurveillanceOfficerListener(LocationForm locationForm) {
		Field districtField = locationForm.getFieldGroup().getField(LocationDto.DISTRICT);
		districtField.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				ComboBox surveillanceOfficerField = (ComboBox) getFieldGroup().getField(EventDto.SURVEILLANCE_OFFICER);
				surveillanceOfficerField.removeAllItems();
				surveillanceOfficerField.addItems(ControllerProvider.getUserController().filterByDistrict(
						assignableSurveillanceOfficers, (DistrictReferenceDto) districtField.getValue()));
			}
		});
	}
	
}
