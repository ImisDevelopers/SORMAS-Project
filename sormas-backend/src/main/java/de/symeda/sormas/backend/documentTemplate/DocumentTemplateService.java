package de.symeda.sormas.backend.documentTemplate;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.symeda.sormas.backend.common.AbstractAdoService;

@Stateless
@LocalBean
public class DocumentTemplateService extends AbstractAdoService<DocumentTemplate> {

    public DocumentTemplateService() {
        super(DocumentTemplate.class);
    }

    public List<DocumentTemplate> workflowQuery(String workflow) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<DocumentTemplate> cq = cb.createQuery(getElementClass());
        Root<DocumentTemplate> from = cq.from(getElementClass());

        cq.select(from).where(cb.equal(from.get("workflow"), workflow));
        return em.createQuery(cq).getResultList();
    }

    public List<DocumentTemplate> getTemplateByUuid(String uuid) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<DocumentTemplate> cq = cb.createQuery(getElementClass());
        Root<DocumentTemplate> from = cq.from(getElementClass());

        cq.select(from).where(cb.equal(from.get("uuid"), uuid));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Predicate createUserFilter(CriteriaBuilder cb, CriteriaQuery cq, From<DocumentTemplate, DocumentTemplate> from) {
        // no filter by user needed
        return null;
    }
}