package org.abondar.experimetnal.javaeedemo.ejbdemo.test;

import org.abondar.experimental.javaeedemo.ejbdemo.ejb.*;
import org.abondar.experimental.javaeedemo.ejbdemo.model.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.NoSuchEJBException;
import javax.inject.Inject;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class EjbTest {
    @Deployment(name = "test.jar")
    public static JavaArchive createDeployment() {
        // explicit archive name required until ARQ-77 is resolved
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Book.class.getPackage())
                .addPackage(BookEJB.class.getPackage())
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource("META-INF/ejb-jar.xml");

    }

    @EJB
    private BookEJB bookEJB;

    @EJB
    private ItemEJB itemEJB;

    @EJB
    private ItemLocal itemLocal;

    @EJB
    private ItemRemote itemRemote;

    @EJB
    private ShoppingCartEJB shoppingCart;

    @EJB
    private CacheEJB cache;

    @EJB
    private ItemSecureEJB itemSecureEJB;

    @Inject
    private ItemAdmin admin;


    @Test
    public void createAndFindBookByEjbTest() throws Exception {
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 100, true);

        Book b = bookEJB.createBook(book);
        assertNotNull("ID should not be null", b.getId());

        assertNotNull(bookEJB.findBookById(b.getId()));
        assertEquals(1, bookEJB.findBooks().size());
        bookEJB.deleteBook(book);

    }

    @Test
    public void createAnItemByEjbTest() throws Exception {
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 100, true);
        book = itemEJB.createBook(book);
        assertNotNull("Book ID should not be null", book.getId());
        assertEquals(1, itemEJB.findBooks().size());
        assertEquals(1, itemLocal.findBooks().size());


        CD cd = new CD("Love SUpreme", 20f, "John Coltrane love moment",
                "Blue Note", 2, 87.45f, "Jazz");

        cd = itemEJB.createCD(cd);
        assertNotNull("CD ID should not be null", cd.getId());
        assertEquals(1, itemRemote.findCDs().size());

        bookEJB.deleteBook(book);

    }

    @Test
    public void secureItemEjbTest() throws Exception {
        admin.call((Callable<Book>) () -> {
            Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 100, true);
            book = itemSecureEJB.createBook(book);
            assertNotNull("Book ID should not be null", book.getId());
            assertEquals(1, itemEJB.findBooks().size());
            assertEquals(1, itemLocal.findBooks().size());
            bookEJB.deleteBook(book);
            return null;
        });

    }

    @Test(expected = NoSuchEJBException.class)
    public void addTwoItemsToTheShoppingCartTest() throws Exception {

        assertEquals(Integer.valueOf(0), shoppingCart.getNumberOfItems());
        assertEquals(Float.valueOf(0), shoppingCart.getTotal());

        Item book = new Item("Cars", 10.0f, "Book of cars");

        shoppingCart.addItem(book);
        assertEquals(Integer.valueOf(1), shoppingCart.getNumberOfItems());
        assertEquals(Float.valueOf(10.0f), shoppingCart.getTotal());

        Item cd = new Item("Mutter", 18f, "Rammstein album");

        shoppingCart.addItem(cd);
        assertEquals(Integer.valueOf(2), shoppingCart.getNumberOfItems());
        assertEquals(Float.valueOf(28.0f), shoppingCart.getTotal());

        shoppingCart.empty();
        assertEquals(Integer.valueOf(0), shoppingCart.getNumberOfItems());
        assertEquals(Float.valueOf(0), shoppingCart.getTotal());

        shoppingCart.checkout();
        shoppingCart.getNumberOfItems();
    }


    @Test
    @Ignore
    public void convertThePriceOfAnItemTest() throws Exception {
        Item book = new Item("Cars", 10.0f, "Book of cars");
        book.setCurrency("Dollars");

        book = itemEJB.convertPrice(book);
        assertEquals("Price should be 12.5 * 0.9", Float.valueOf(11.25f), book.getPrice());
    }

    @Test
    public void addRemoveAndGetThingsFromTheCacheTest() throws Exception {
        assertEquals(Integer.valueOf(2), cache.getNumberOfItems());

        cache.addToCache(3L, "Third item in the cache");
        assertEquals(Integer.valueOf(3), cache.getNumberOfItems());
        assertEquals("First item in the cache", cache.getFromCache(1L));

        cache.addToCache(4L, "Fourth item in the cache");
        assertEquals(Integer.valueOf(4), cache.getNumberOfItems());
        assertEquals("Fourth item in the cache", cache.getFromCache(4L));

        cache.removeFromCache(3L);
        assertEquals(Integer.valueOf(3), cache.getNumberOfItems());
    }

}