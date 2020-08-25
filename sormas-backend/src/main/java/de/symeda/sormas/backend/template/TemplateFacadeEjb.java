package de.symeda.sormas.backend.template;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "TemplateFacade")
public class TemplateFacadeEjb {
    @EJB
    private TemplateService templateService;

    public Template saveTemplate(Template template) {
        templateService.ensurePersisted(template);
        return template;
    }

    public void deleteTemplate(String uuid) {
        List<Template> tList = templateService.getTemplateByUuid(uuid);
        Template toDelete = tList.get(0);
        templateService.delete(toDelete);
    }

    public List<Template> templateWorkflowQuery(String workflow){
        return templateService.workflowQuery(workflow);
    }

    public Template getTemplateByUuid(String uuid){
        List<Template> tList = templateService.getTemplateByUuid(uuid);
        return tList.get(0);
    }
}