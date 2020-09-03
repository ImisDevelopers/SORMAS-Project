package de.symeda.sormas.backend.documentTemplate;

import de.symeda.sormas.api.documentTemplate.DocumentTemplateDto;
import de.symeda.sormas.backend.AbstractBeanTest;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DocumentTemplateFacadeEjbTest extends AbstractBeanTest {

    @Test
    public void testTemplateCreation() throws IOException {
        DocumentTemplate temp = new DocumentTemplate();
        XWPFDocument doc = new XWPFDocument();
        temp.writeXWPFDocument(doc);

        DocumentTemplateDto template = DocumentTemplateDto.build("testing1", "Test/test", temp.getDocument());
        String uID = template.getUuid();

        getDocumentTemplateFacade().saveTemplate(template);

        assertNotNull(getDocumentTemplateFacade().getTemplateByUuid(uID));

        getDocumentTemplateFacade().deleteTemplate(uID);

        assertNull(getDocumentTemplateFacade().getTemplateByUuid(uID));
    }
}