package de.gedoplan.javaee.repository;

import de.gedoplan.javaee.model.Author;
import de.gedoplan.javaee.model.Author_;
import de.gedoplan.javaee.model.ListValue;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationScoped
public class AuthorRepository {

    @PersistenceContext(unitName = "demo")
    private EntityManager em;

    public List<ListValue> getListValues() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<ListValue> query = builder.createQuery(ListValue.class);
        Root<Author> root = query.from(Author.class);
        query.select(builder.construct(ListValue.class, root.get(Author_.id), builder.concat(root.get(Author_.firstname), root.get(Author_.lastname))));

        return this.em.createQuery(query).getResultList();
    }

    public Author getAuthorById(Integer id) {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Author> query = builder.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);
        query.select(root).distinct(true);
        root.fetch(Author_.books);
        query.where(builder.equal(root.get(Author_.id), id));
        
        Author author;
        try {
            return this.em.createQuery(query).getSingleResult();
        } catch (EntityNotFoundException ex) {
            author = null;
        }
        return author;
    }

    @Transactional
    public Author merge(Author author) {
        return this.em.merge(author);
    }
}
