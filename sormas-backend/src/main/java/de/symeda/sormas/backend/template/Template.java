package de.symeda.sormas.backend.template;

import javax.persistence.Entity;

import org.w3c.dom.Document;

import de.symeda.sormas.backend.common.AbstractDomainObject;

@Entity
public class Template extends AbstractDomainObject {
    public static final String TABLE_NAME = "template";

    public static final String WORKFLOW = "workflow";
    public static final String DOCUMENT = "document";

    private String workflow;
    private Document document;

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}