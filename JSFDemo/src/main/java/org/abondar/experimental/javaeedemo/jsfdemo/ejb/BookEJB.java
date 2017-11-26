package org.abondar.experimental.javaeedemo.jsfdemo.ejb;



import org.abondar.experimental.javaeedemo.jsfdemo.model.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
public class BookEJB {

    @PersistenceContext(unitName = "demo_unit")
    private EntityManager em;

    public Book findBookById(Long id) {
        return em.find(Book.class, id);
    }

    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }

    public List<Book> findBooks() {
        TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class);
        return query.getResultList();
    }


    public void deleteBook(Book book) {
        em.remove(em.merge(book));
    }

    public Book updateBook(Book book) {
        return em.merge(book);
    }
}