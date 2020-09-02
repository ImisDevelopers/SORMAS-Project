package de.symeda.sormas.backend.documentTemplate;

import javax.persistence.Entity;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import de.symeda.sormas.backend.common.AbstractDomainObject;
import org.hibernate.annotations.Type;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Entity
public class DocumentTemplate extends AbstractDomainObject {
    public static final String TABLE_NAME = "doctemplates";

    public static final String WORKFLOW = "workflow";
    public static final String FILENAME = "filename";
    public static final String DOCUMENT = "document";

    private String workflow;
    private String filename;
    private byte[] document;

    @Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public XWPFDocument readXWPFDocument() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(document);
        return new XWPFDocument(byteArrayInputStream);
    }

    public void writeXWPFDocument(XWPFDocument document) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        byteArrayOutputStream.close();
        document.close();
        this.document = byteArrayOutputStream.toByteArray();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}