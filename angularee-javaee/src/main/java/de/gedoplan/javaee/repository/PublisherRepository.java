package de.gedoplan.javaee.repository;

import de.gedoplan.javaee.model.ListValue;
import de.gedoplan.javaee.model.Publisher;
import de.gedoplan.javaee.model.Publisher_;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationScoped
public class PublisherRepository {

    @PersistenceContext(unitName = "demo")
    private EntityManager em;

    public List<ListValue> getListValues() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<ListValue> query = builder.createQuery(ListValue.class);
        Root<Publisher> root = query.from(Publisher.class);
        query.select(builder.construct(ListValue.class, root.get(Publisher_.id), root.get(Publisher_.name)));

        return this.em.createQuery(query).getResultList();
    }
}
