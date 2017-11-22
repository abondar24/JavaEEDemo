package org.abondar.experimetnal.javaeedemo.ejbdemo.test;

import org.abondar.experimental.javaeedemo.ejbdemo.ejb.BookEJB;
import org.abondar.experimental.javaeedemo.ejbdemo.model.Book;
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
public class BookTest {
    @Deployment(name = "test.jar")
    public static JavaArchive createDeployment() {
        // explicit archive name required until ARQ-77 is resolved
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addPackage(Book.class.getPackage())
                .addPackage(BookEJB.class.getPackage())
                .addAsResource("META-INF/persistence.xml");

    }

    @EJB
    BookEJB bookEJB;

    @Test
    public void createAndFindBookByEjbTest() throws Exception {
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 100, true);

        Book b = bookEJB.createBook(book);
        assertNotNull("ID should not be null", b.getId());

        assertNotNull(bookEJB.findBookById(b.getId()));
        assertEquals(1, bookEJB.findBooks().size());

    }
}