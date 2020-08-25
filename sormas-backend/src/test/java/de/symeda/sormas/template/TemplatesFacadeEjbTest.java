package de.symeda.sormas.backend.templates;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import de.symeda.sormas.backend.template.Template;
import de.symeda.sormas.backend.template.TemplateService;
import de.symeda.sormas.backend.template.TemplateFacadeEjb;

import org.junit.Test;

import de.symeda.sormas.backend.AbstractBeanTest;

public class TemplatesFacadeEjbTest extends AbstractBeanTest {

    @Test
    public void testTemplateCreation() throws Exception{
        TemplateFacadeEjb tf = new TemplateFacadeEjb();
        Template template = new Template();
        template.setWorkflow("testing1");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.newDocument();
        doc.createAttribute("testAttribute");
        template.setDocument(doc);

        template.setUuid("1");

        tf.saveTemplate(template);

        assertNotNull(tf.getTemplateByUuid("1"));

        tf.deleteTemplate("1");

        assertNull(tf.getTemplateByUuid("1"));
    }
}