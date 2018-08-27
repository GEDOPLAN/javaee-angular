
package de.gedoplan.javaee.model;

import com.fasterxml.jackson.annotation.JsonView;
import de.gedoplan.javaee.jackson.views.GlobalViews;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@Entity
public class Publisher extends  IntegerIdEntity{
    
    private String name;
    
    @OneToMany(mappedBy = "publisher")
    @JsonView(GlobalViews.Detail.class)
    private Set<Book> books;
    
    public void addBook(Book book){
        if (this.books==null){
            this.books=new HashSet<>();
        }
        
        this.books.add(book);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    
    
}
