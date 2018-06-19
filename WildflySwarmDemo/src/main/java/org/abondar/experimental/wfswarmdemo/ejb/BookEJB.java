package org.abondar.experimental.wfswarmdemo.ejb;

import org.abondar.experimental.wfswarmdemo.model.Book;
import org.abondar.experimental.wfswarmdemo.model.Books;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class BookEJB {

    @PersistenceContext(unitName = "demo_unit")
    private EntityManager em;


    public Book getBook(Long bookId) {
        return em.find(Book.class, bookId);
    }

    public Books getBooks() {
        TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class);
        return new Books(query.getResultList());
    }


    public String createBook(Book book) {
        em.persist(book);
        return book.getId().toString();
    }

    public void deleteBook( Long bookId) {
        em.remove(em.find(Book.class, bookId));

    }

}
