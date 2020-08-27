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
    public void testTemplateCreation() throws Exception{
        DocumentTemplateFacadeEjb tf = new DocumentTemplateFacadeEjb();
        DocumentTemplate template = new DocumentTemplate();
        template.setWorkflow("testing1");

        Document doc = new XWPFDocument();
        template.setDocument(doc);

        template.setUuid("1");
        template.setId(1);
        Timestamp ts = new Timestamp(50000000);
        template.setCreationDate(ts);
        template.setChangeDate(ts);
        tf.saveTemplate(template);

        assertNotNull(tf.getTemplateByUuid("1"));

        tf.deleteTemplate("1");

        assertNull(tf.getTemplateByUuid("1"));
    }
}