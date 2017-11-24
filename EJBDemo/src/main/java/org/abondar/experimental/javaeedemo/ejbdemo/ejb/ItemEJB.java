package org.abondar.experimental.javaeedemo.ejbdemo.ejb;

import org.abondar.experimental.javaeedemo.ejbdemo.model.Book;
import org.abondar.experimental.javaeedemo.ejbdemo.model.CD;
import org.abondar.experimental.javaeedemo.ejbdemo.model.Item;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Currency;
import java.util.List;


@Stateless
@LocalBean
@Remote(ItemRemote.class)
@RolesAllowed({"user", "employee", "admin"})
public class ItemEJB implements ItemLocal, ItemRemote {

    @PersistenceContext(unitName = "demo_unit")
    private EntityManager em;

    @Resource(name = "currencyEntry")
    private String currency;

    @Resource(name = "changeRateEntry")
    private Float changeRate;

    @PermitAll
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


    @Override
    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }


    @Override
    public CD createCD(CD cd) {
        em.persist(cd);
        return cd;
    }

    @RolesAllowed("admin")
    public void deleteCD(CD cd) {
        em.remove(em.merge(cd));
    }

    public Item convertPrice(Item item){
        item.setPrice(item.getPrice()*changeRate);
        item.setCurrency(currency);
        return item;
    }

}
