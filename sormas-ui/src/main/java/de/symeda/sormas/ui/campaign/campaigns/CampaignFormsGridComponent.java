package de.symeda.sormas.ui.campaign.campaigns;

import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.GridDragEndListener;

import de.symeda.sormas.api.campaign.form.CampaignFormMetaReferenceDto;
import de.symeda.sormas.api.i18n.Captions;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.i18n.Strings;
import de.symeda.sormas.ui.utils.AbstractEditableGrid;

@SuppressWarnings("serial")
public class CampaignFormsGridComponent extends AbstractEditableGrid<CampaignFormMetaReferenceDto> {

	public CampaignFormsGridComponent(
		List<CampaignFormMetaReferenceDto> savedCampaignFormMetas,
		List<CampaignFormMetaReferenceDto> allCampaignFormMetas) {

		super(savedCampaignFormMetas, allCampaignFormMetas);
		setWidth(40, Unit.PERCENTAGE);
	}

	@Override
	protected Button.ClickListener newRowEvent() {
		return event -> {
			items.add(new CampaignFormMetaReferenceDto());
			grid.setItems(items);
		};
	}

	@Override
	protected Binder<CampaignFormMetaReferenceDto> addColumnsBinder(List<CampaignFormMetaReferenceDto> allElements) {
		final Binder<CampaignFormMetaReferenceDto> binder = new Binder<>();

		ComboBox<CampaignFormMetaReferenceDto> formCombo = new ComboBox<>(Strings.entityCampaignDataForm, allElements);
		Binder.Binding<CampaignFormMetaReferenceDto, CampaignFormMetaReferenceDto> formBind = binder
			.bind(formCombo, campaignFormMetaReferenceDto -> campaignFormMetaReferenceDto, (bindedCampaignFormMeta, selectedCampaignFormMeta) -> {
				bindedCampaignFormMeta.setUuid(selectedCampaignFormMeta.getUuid());
				bindedCampaignFormMeta.setCaption(selectedCampaignFormMeta.getCaption());
			});
		Grid.Column<CampaignFormMetaReferenceDto, String> formColumn =
			grid.addColumn(campaignFormMetaReferenceDto -> campaignFormMetaReferenceDto.getCaption())
				.setCaption(I18nProperties.getString(Strings.entityCampaignDataForm));
		formColumn.setEditorBinding(formBind);
		return binder;
	}

	public List<CampaignFormMetaReferenceDto> getItems() {
		return items;
	}

	protected String getHeaderString() {
		return Strings.headingCampaignData;
	}

	protected String getAdditionalRowCaption() {
		return Captions.campaignAdditionalForm;
	}

	@Override
	protected GridDragEndListener<CampaignFormMetaReferenceDto> gridDragEndListener() {
		return gridDragEndEvent -> {
		};
	}
}
