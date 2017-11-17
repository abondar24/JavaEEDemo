package org.abondar.experimental.javaeedemo.ormdemo.test;

import org.abondar.experimental.javaeedemo.ormdemo.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersistenceTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test_unit");
    private EntityManager em;
    private EntityTransaction tx;


    @Before
    public void initEntityManager() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() throws SQLException {
        if (em != null) em.close();
    }


    @Test
    public void createBookTest() {
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 100, true);
        book.setId(getRandomId());
        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID should not be null", book.getId());
    }



    @Test
    public void createAddressTest() {
        Address address = new Address("111 Salo Way", "Leningrad", "VA", "ZIPCODE", "USA");
        address.setId(getRandomId());
        tx.begin();
        em.persist(address);
        tx.commit();
        assertNotNull("ID should not be null", address.getId());
    }

    @Test
    public void createNewsEmbedTest() {

        NewsEmbed news = new NewsEmbed(new NewsIdEmbed("What the hell is going?", "EN"), "Some crazy guys ran naked");
        tx.begin();
        em.persist(news);
        tx.commit();
        news = em.find(NewsEmbed.class, new NewsIdEmbed("What the hell is going?", "EN"));

        assertEquals("Some crazy guys ran naked", news.getContent());
    }

    @Test
    public void createNewsIcTest() {
        News news = new News("What the hell is going?", "EN", "Penguin was killed in the flat");
        tx.begin();
        em.persist(news);
        tx.commit();

        assertNotNull("ID should not be null", news.getTitle());
    }

    @Test
    public void createTrackTest() {
        Track track = new Track("Mutter", 4.33f, "Main song of Rammstein's albmum Mutter");
        tx.begin();
        em.persist(track);
        tx.commit();

        assertNotNull("ID should not be null", track.getId());

    }

    @Test
    public void createCustomerTest(){
        Customer customer = new Customer("John", "Smith", "jsmith@gmail.com", "1234565", new Date(), new Date());
        tx.begin();
        em.persist(customer);
        tx.commit();
        assertNotNull("ID should not be null", customer.getId());
    }

    private Long getRandomId() {
        return Math.abs(new Random().nextLong());
    }
}
