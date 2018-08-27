package de.gedoplan.javaee.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.gedoplan.javaee.jackson.resolver.JPAEntityIDResolver;
import de.gedoplan.javaee.jackson.views.GlobalViews;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Entity
public class Book extends IntegerIdEntity {

    private String title;

    private Integer pages;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonView(GlobalViews.Detail.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(
            resolver = JPAEntityIDResolver.class,
            generator = ObjectIdGenerators.PropertyGenerator.class,
            scope = Author.class,
            property = "id"
    )
    private Set<Author> authors;

    @ManyToOne()
    @JsonView(GlobalViews.Detail.class)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIdentityInfo(
            resolver = JPAEntityIDResolver.class,
            generator = ObjectIdGenerators.PropertyGenerator.class,
            scope = Publisher.class,
            property = "id"
    )
    private Publisher publisher;

    public void addAuthor(Author author) {
        this.getAuthors().add(author);
        author.getBooks().add(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Set<Author> getAuthors() {
        if (this.authors == null) {
            this.authors = new HashSet<>();
        }
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
