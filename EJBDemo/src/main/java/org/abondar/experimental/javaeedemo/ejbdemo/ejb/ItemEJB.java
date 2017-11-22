package org.abondar.experimental.javaeedemo.ejbdemo.ejb;

import org.abondar.experimental.javaeedemo.ejbdemo.model.Book;
import org.abondar.experimental.javaeedemo.ejbdemo.model.CD;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless
@LocalBean
@Remote(ItemRemote.class)
public class ItemEJB implements ItemLocal,ItemRemote{

    @PersistenceContext(unitName = "demo_unit")
    private EntityManager em;

    @Override
    public List<Book> findBooks() {
        TypedQuery<Book> query = em.createNamedQuery("findAllBooks", Book.class);
        return query.getResultList();
    }

    @Override
    public List<CD> findCDs() {
        TypedQuery<CD> query = em.createNamedQuery("findAllCDs", CD.class);
        return query.getResultList();
    }


    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }


    public CD createCD(CD cd) {
        em.persist(cd);
        return cd;
    }

    public void deleteCD(CD cd) {
        em.remove(em.merge(cd));
    }
}
