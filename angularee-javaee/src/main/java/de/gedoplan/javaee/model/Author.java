package de.gedoplan.javaee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import de.gedoplan.javaee.jackson.views.GlobalViews;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAppend(
    attrs = {
        @JsonAppend.Attr(value = "bookcount")
    }
)
public class Author extends IntegerIdEntity {
    
    private String firstname;
    
    @JsonView(GlobalViews.Overview.class)
    private String lastname;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "authors")
    @JsonView(GlobalViews.Detail.class)
    private Set<Book> books; 

    public void addBook(Book book) {
        this.getBooks().add(book);
        book.getAuthors().add(this);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Book> getBooks() {
        if (this.books == null) {
            this.books = new HashSet<>();
        }
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

}
