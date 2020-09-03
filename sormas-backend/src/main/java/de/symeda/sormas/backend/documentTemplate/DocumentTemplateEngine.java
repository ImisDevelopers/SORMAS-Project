package de.symeda.sormas.backend.documentTemplate;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.FieldExtractor;
import fr.opensagres.xdocreport.template.FieldsExtractor;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DocumentTemplateEngine {
    public static Set<String> getPlaceholders(String templatePath) throws IOException, XDocReportException {
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
                new FileInputStream(templatePath),
                TemplateEngineKind.Velocity);
        FieldsExtractor<FieldExtractor> extractor = FieldsExtractor.create();
        report.extractFields(extractor);

        Set<String> placeholders = new HashSet<>();
        for (FieldExtractor field: extractor.getFields()) {
            placeholders.add(field.getName());
        }
        return placeholders;
    }

    public static String generateDocument(Map<String, String> replacements, String templatePath, String outputDirectory) throws IOException, XDocReportException {
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(
                new FileInputStream(templatePath),
                TemplateEngineKind.Velocity);

        IContext context = report.createContext();
        for (String key : replacements.keySet()) {
            context.put(key, replacements.get(key));
        }

        String outputFile = outputDirectory + File.separator + "Out_" + FilenameUtils.getBaseName(templatePath) + ".docx";
        File file = new File(outputFile);
        report.process(context, new FileOutputStream(file));

        return outputFile;
    }
}
