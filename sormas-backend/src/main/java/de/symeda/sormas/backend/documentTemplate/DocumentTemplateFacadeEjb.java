package de.symeda.sormas.backend.documentTemplate;

import de.symeda.sormas.api.documentTemplate.DocTemplateFacade;
import de.symeda.sormas.api.documentTemplate.DocumentTemplateDto;
import de.symeda.sormas.backend.util.DtoHelper;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless(name = "DocTemplateFacade")
public class DocumentTemplateFacadeEjb implements DocTemplateFacade {

    @EJB
    private DocumentTemplateService templateService;

    public DocumentTemplateDto saveTemplate(DocumentTemplateDto template) {
        templateService.ensurePersisted(fromDto(template));
        return template;
    }

    public void deleteTemplate(String uuid) {
        List<DocumentTemplate> tList = templateService.getTemplateByUuid(uuid);
        DocumentTemplate toDelete = tList.get(0);
        templateService.delete(toDelete);
    }

    public List<DocumentTemplateDto> templateWorkflowQuery(String workflow){
        List<DocumentTemplate> result = templateService.workflowQuery(workflow);
        return result.stream().map(DocumentTemplateFacadeEjb::toDto).collect(Collectors.toList());
    }

    public DocumentTemplateDto getTemplateByUuid(String uuid){
        List<DocumentTemplate> result = templateService.getTemplateByUuid(uuid);
        DocumentTemplate res = result.get(0);
        return toDto(res);
    }

    public static DocumentTemplateDto toDto(DocumentTemplate source){

        if (source == null) {
            return null;
        }

        DocumentTemplateDto target = new DocumentTemplateDto();
        DtoHelper.fillDto(target, source);

        target.setDocument(source.getDocument());
        target.setFilename(source.getFilename());
        target.setWorkflow(source.getWorkflow());

        return target;
    }

    public DocumentTemplate fromDto(DocumentTemplateDto source) {

        if (source == null) {
            return null;
        }

        DocumentTemplate target = templateService.getByUuid(source.getUuid());
        if (target == null) {
            target = new DocumentTemplate();
            target.setUuid(source.getUuid());
            if (source.getCreationDate() != null) {
                target.setCreationDate(new Timestamp(source.getCreationDate().getTime()));
            }
        }
        DtoHelper.validateDto(source, target);

        target.setDocument(source.getDocument());
        target.setWorkflow(source.getWorkflow());
        target.setFilename(source.getFilename());

        return target;
    }
}