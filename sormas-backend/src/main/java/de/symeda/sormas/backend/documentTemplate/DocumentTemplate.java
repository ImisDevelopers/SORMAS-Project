package de.symeda.sormas.backend.documentTemplate;

import javax.persistence.Entity;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import de.symeda.sormas.backend.common.AbstractDomainObject;

@Entity
public class DocumentTemplate extends AbstractDomainObject {
    public static final String TABLE_NAME = "doctemplates";

    public static final String WORKFLOW = "workflow";
    public static final String FILENAME = "filename";
    public static final String DOCUMENT = "document";

    private String workflow;
    private String filename;
    private XWPFDocument document;

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public XWPFDocument getDocument() {
        return document;
    }

    public void setDocument(XWPFDocument document) {
        this.document = document;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}