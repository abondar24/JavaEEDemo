package org.abondar.experimental.javaeedemo.jpademo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookTest {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test_unit");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEM() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEM() throws Exception {
        if (em != null) {
            em.close();
        }
    }

    @Test
    public void findJavaEE7Book() throws Exception {
        Book book = em.find(Book.class, 1001L);
        assertEquals("Beginning Java EE 7", book.getTitle());
    }

    @Test
    public void createCarsBook() throws Exception {
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 100, true);

        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID should not be null", book.getId());

        book = em.createNamedQuery("findBookCars", Book.class).getSingleResult();
        assertEquals("The book of cars", book.getDescription());

    }

    @Test(expected = ConstraintViolationException.class)
    public void raiseConstraintViolationExceptionNullTitle() {
        Book book = new Book(null, 10.0f, "The book of cars", "1-84023-742-2", 100, true);
        em.persist(book);
    }

}
