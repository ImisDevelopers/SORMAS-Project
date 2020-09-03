package de.symeda.sormas.backend.documentTemplate;

import fr.opensagres.xdocreport.core.XDocReportException;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class DocumentTemplateEngineTest {
    @Test
    public void readVariablesFromDocxDocument() throws IOException, XDocReportException {
        String filePath = DocumentTemplateEngineTest.class.getResource("/DocumentTemplate.docx").getPath();
        Set<String> placeholders = DocumentTemplateEngine.getPlaceholders(filePath);
        for (String placeholder : placeholders) {
            System.out.println(placeholder);
        }

        assertTrue(placeholders.contains("{name}"));
        assertTrue(placeholders.contains("{quarantine.to}"));
        assertTrue(placeholders.contains("{quarantine.from}"));

        // What to do with this???
        assertTrue(placeholders.contains("{___NoEscapeStylesGenerator.generateAllStyles($___DefaultStyle)}"));
    }

    @Test
    public void processDocxTemplate() throws IOException, XDocReportException {
        String filePath = DocumentTemplateEngineTest.class.getResource("/DocumentTemplate.docx").getPath();

        Map<String, String> context = new HashMap<>();
        context.put("name", "Max Mustermann");
        context.put("quarantine.to", "2020/09/03");
        context.put("quarantine.from", "2020/09/17");

        DocumentTemplateEngine.generateDocument(context, filePath, ".");
    }
}
