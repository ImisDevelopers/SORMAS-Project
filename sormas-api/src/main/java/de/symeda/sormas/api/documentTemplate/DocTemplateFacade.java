package de.symeda.sormas.api.documentTemplate;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface DocTemplateFacade {

    DocumentTemplateDto saveTemplate(DocumentTemplateDto template);

    void deleteTemplate(String uuid);

    List<DocumentTemplateDto> templateWorkflowQuery(String workflow);

    DocumentTemplateDto getTemplateByUuid(String uuid);
}
