package de.gedoplan.javaee.repository;

import de.gedoplan.javaee.model.Book;
import de.gedoplan.javaee.model.Book_;
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
public class BookRepository {

    @PersistenceContext(unitName = "demo")
    private EntityManager em;

    public List<Book> getBooks() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        query.select(root);
        return this.em.createQuery(query).getResultList();
    }

    public Book getBookById(Integer id) {
        Book book;
        try {
            CriteriaBuilder builder = this.em.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.select(root).distinct(true);
            query.where(builder.equal(root.get(Book_.id), id));
            root.fetch(Book_.authors);
            root.fetch(Book_.publisher);
            book = this.em.createQuery(query).getSingleResult();
        } catch (EntityNotFoundException ex) {
            book = null;
        }
        return book;
    }

    @Transactional
    public Book merge(Book book) {
        return this.em.merge(book);
    }
}
