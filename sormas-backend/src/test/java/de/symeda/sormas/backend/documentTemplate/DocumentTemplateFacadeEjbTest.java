package de.symeda.sormas.backend.documentTemplate;

import java.sql.Timestamp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import de.symeda.sormas.backend.documentTemplate.DocumentTemplate;
import de.symeda.sormas.backend.documentTemplate.DocumentTemplateService;
import de.symeda.sormas.backend.documentTemplate.DocumentTemplateFacadeEjb;

import org.junit.Test;

import de.symeda.sormas.backend.AbstractBeanTest;

public class DocumentTemplateFacadeEjbTest extends AbstractBeanTest {

    @Test
    public void testTemplateCreation() {
        DocumentTemplate template = new DocumentTemplate();
        template.setWorkflow("testing1");
        XWPFDocument doc = new XWPFDocument();
        template.setDocument(doc);

        template.setUuid("1");
        template.setId(1L);
        Timestamp ts = new Timestamp(50000000);
        template.setCreationDate(ts);
        template.setChangeDate(ts);
        getDocumentTemplateFacade().saveTemplate(template);

        assertNotNull(getDocumentTemplateFacade().getTemplateByUuid("1"));

        getDocumentTemplateFacade().deleteTemplate("1");

        assertNull(getDocumentTemplateFacade().getTemplateByUuid("1"));
    }
}