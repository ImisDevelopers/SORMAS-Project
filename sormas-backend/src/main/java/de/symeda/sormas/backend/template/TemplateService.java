package de.symeda.sormas.backend.template;

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
public class TemplateService extends AbstractAdoService<Template> {

    public TemplateService() {
        super(Template.class);
    }

    public List<Template> workflowQuery(String workflow) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Template> cq = cb.createQuery(getElementClass());
        Root<Template> from = cq.from(getElementClass());

        cq.select(from).where(cb.equal(from.get("workflow"), workflow));
        return em.createQuery(cq).getResultList();
    }

    public List<Template> getTemplateByUuid(String uuid) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Template> cq = cb.createQuery(getElementClass());
        Root<Template> from = cq.from(getElementClass());

        cq.select(from).where(cb.equal(from.get("uuid"), uuid));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Predicate createUserFilter(CriteriaBuilder cb, CriteriaQuery cq, From<Template, Template> from) {
        // no filter by user needed
        return null;
    }
}