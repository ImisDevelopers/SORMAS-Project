package de.symeda.sormas.api.documentTemplate;

import de.symeda.sormas.api.EntityDto;

import de.symeda.sormas.api.utils.DataHelper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class DocumentTemplateDto extends EntityDto{
    public static final String I18N_PREFIX = "DocumentTemplate";

    private String workflow;
    private String filename;
    private byte[] document;

    public static DocumentTemplateDto build(String workflow, String filename, byte[] document) {

        DocumentTemplateDto docTemplate = new DocumentTemplateDto();
        docTemplate.setUuid(DataHelper.createUuid());
        docTemplate.setWorkflow(workflow);
        docTemplate.setFilename(filename);
        docTemplate.setDocument(document);
        return docTemplate;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}
