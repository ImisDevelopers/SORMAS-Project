package de.symeda.sormas.ui.caze;

import org.junit.Before;

import de.symeda.sormas.ui.AbstractBeanTest;
import de.symeda.sormas.ui.SormasUI;

public class CaseControllerTest extends AbstractBeanTest {

	private SormasUI ui;

	@Before
	public void initUI() throws Exception {

		/*
		 * creator.createUser(null, null, null, "ad", "min", UserRole.ADMIN, UserRole.NATIONAL_USER);
		 * VaadinRequest request = Mockito.mock(VaadinServletRequest.class);
		 * when(request.getUserPrincipal()).thenReturn((Principal) () -> "admin");
		 * CurrentInstance.set(VaadinRequest.class, request);
		 * VaadinService service = Mockito.mock(VaadinService.class);
		 * CurrentInstance.set(VaadinService.class, service);
		 * VaadinSession session = Mockito.mock(VaadinSession.class);
		 * ConverterFactory converterFactory = new SormasDefaultConverterFactory();
		 * when(session.getConverterFactory()).thenReturn(converterFactory);
		 * when(session.getService()).thenReturn(service);
		 * CurrentInstance.set(VaadinSession.class, session);
		 * ui = new SormasUI();
		 * CurrentInstance.set(UI.class, ui);
		 * java.lang.reflect.Field pageField = UI.class.getDeclaredField("page");
		 * pageField.setAccessible(true);
		 * pageField.set(ui, Mockito.mock(Page.class));
		 */
	}

//	@SuppressWarnings("unchecked")
//	@Test
//	public void testGetCaseCreateComponent() {
//
//		CaseController controller = new CaseController();
//		CommitDiscardWrapperComponent<CaseCreateForm> caseCreateComponent = controller.getCaseCreateComponent(null,
//				null, null, null);
//
//		// TODO add UI class and attach form, so everything works as expected
//		CaseCreateForm caseCreateForm = caseCreateComponent.getWrappedComponent();
//		((Field<String>) caseCreateForm.getField(CaseCreateForm.FIRST_NAME)).setValue("Steff");
//		((Field<String>) caseCreateForm.getField(CaseCreateForm.LAST_NAME)).setValue("Steffson");
//		((Field<Disease>) caseCreateForm.getField(CaseDataDto.DISEASE)).setValue(Disease.EVD);
//
//		caseCreateComponent.commit();
//	}
}
