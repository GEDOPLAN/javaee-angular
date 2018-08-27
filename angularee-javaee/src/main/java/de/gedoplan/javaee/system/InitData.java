package de.gedoplan.javaee.system;

import de.gedoplan.javaee.model.Author;
import de.gedoplan.javaee.model.Book;
import de.gedoplan.javaee.model.Publisher;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author GEDOPLAN, Dominik Mathmann
 */
@ApplicationScoped
public class InitData {
        @PersistenceContext(unitName = "demo")
        private EntityManager em;
    
        @Transactional
        public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
             if(this.em.createQuery("select count(b) from Book b", Long.class).getSingleResult()>0){
                 return;
             }
            
            Publisher publisher01=new Publisher();
            publisher01.setName("Entwickler.Press");
            publisher01=this.em.merge(publisher01);
            Publisher publisher02=new Publisher();
            publisher02.setName("lulu.com");
            publisher02=this.em.merge(publisher02);
            
            Author author01=new Author();
            author01.setFirstname("Adam");
            author01.setLastname("Bean");
            author01=this.em.merge(author01);
            
            Author author02=new Author();
            author02.setFirstname("James");
            author02.setLastname("Gosling");
            author02=this.em.merge(author02);
            
            Book book01=new Book();
            book01.addAuthor(author01);
            author01.addBook(book01);
            book01.setPublisher(publisher01);
            book01.setTitle("Real World Java EE Patterns");
            book01.setPages(540);
            book01=this.em.merge(book01);
            
            Book book02=new Book();
            book02.addAuthor(author01);
            author01.addBook(book02);
            book02.setPublisher(publisher01);
            book02.setTitle("Enterprise Architekturen. Leitfaden für effiziente Software-Entwicklung");
            book02.setPages(720);
            book02=this.em.merge(book01);
            
            Book book03=new Book();
            book03.addAuthor(author01);
            author01.addBook(book03);
            book03.addAuthor(author02);
            author02.addBook(book03);
            book03.setPublisher(publisher02);
            book03.setTitle("Real World Java EE Night Hacks");
            book03.setPages(375);
            book03=this.em.merge(book03);
            
    }
 
}
