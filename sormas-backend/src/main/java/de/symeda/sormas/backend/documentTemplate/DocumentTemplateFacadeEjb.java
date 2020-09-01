package de.symeda.sormas.backend.documentTemplate;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "DocumentTemplateFacade")
public class DocumentTemplateFacadeEjb {
    @EJB
    private DocumentTemplateService templateService;

    public DocumentTemplate saveTemplate(DocumentTemplate template) {
        templateService.ensurePersisted(template);
        return template;
    }

    public void deleteTemplate(String uuid) {
        List<DocumentTemplate> tList = templateService.getTemplateByUuid(uuid);
        DocumentTemplate toDelete = tList.get(0);
        templateService.delete(toDelete);
    }

    public List<DocumentTemplate> templateWorkflowQuery(String workflow){
        return templateService.workflowQuery(workflow);
    }

    public DocumentTemplate getTemplateByUuid(String uuid){
        List<DocumentTemplate> tList = templateService.getTemplateByUuid(uuid);
        return tList.get(0);
    }
}