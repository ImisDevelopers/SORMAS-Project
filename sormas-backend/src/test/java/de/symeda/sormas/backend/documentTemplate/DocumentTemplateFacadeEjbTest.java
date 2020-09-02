package de.symeda.sormas.backend.documentTemplate;

import java.io.IOException;
import java.sql.Timestamp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import de.symeda.sormas.api.utils.DataHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.junit.Test;

import de.symeda.sormas.backend.AbstractBeanTest;

public class DocumentTemplateFacadeEjbTest extends AbstractBeanTest {

    @Test
    public void testTemplateCreation() throws IOException {
        DocumentTemplate template = new DocumentTemplate();
        template.setWorkflow("testing1");
        XWPFDocument doc = new XWPFDocument();
        template.writeXWPFDocument(doc);

        String uuid = DataHelper.createUuid();
        template.setUuid(uuid);
        Timestamp ts = new Timestamp(50000000);
        template.setCreationDate(ts);
        template.setChangeDate(ts);
        getDocumentTemplateFacade().saveTemplate(template);

        assertNotNull(getDocumentTemplateFacade().getTemplateByUuid(uuid));

        getDocumentTemplateFacade().deleteTemplate(uuid);

        assertNull(getDocumentTemplateFacade().getTemplateByUuid(uuid));
    }
}