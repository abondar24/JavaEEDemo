package org.abondar.experimetnal.javaeedemo.ejbdemo.test;

import org.abondar.experimental.javaeedemo.ejbdemo.ejb.BookEJB;
import org.abondar.experimental.javaeedemo.ejbdemo.ejb.ItemEJB;
import org.abondar.experimental.javaeedemo.ejbdemo.ejb.ItemLocal;
import org.abondar.experimental.javaeedemo.ejbdemo.ejb.ItemRemote;
import org.abondar.experimental.javaeedemo.ejbdemo.model.Book;
import org.abondar.experimental.javaeedemo.ejbdemo.model.CD;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

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
                .addAsResource("META-INF/test-persistence.xml","META-INF/persistence.xml");

    }

    @EJB
    BookEJB bookEJB;

    @EJB
    ItemEJB itemEJB;

    @EJB
    ItemLocal itemLocal;

    @EJB
    ItemRemote itemRemote;

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
}