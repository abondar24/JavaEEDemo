package org.abondar.experimental.javaeedemo.ormdemo.test;

import org.abondar.experimental.javaeedemo.ormdemo.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.*;
import java.sql.SQLException;
import java.util.*;

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
        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("ID should not be null", book.getId());
    }


    @Test
    public void findJavaEE7Book() throws Exception {
        Book book = em.find(Book.class, 1001L);
        assertEquals("Beginning Java EE 7", book.getTitle());
    }

    @Test(expected = ConstraintViolationException.class)
    public void nullTitleValidationTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Book book = new Book(null, 10.0f, "The book of cars", "1-84023-742-2", 100, true);
        tx.begin();
        em.persist(book);
        tx.commit();
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertEquals(1,violations.size());
    }

    @Test
    public void createBookWithTagsTest(){
        List<String> tags = Arrays.asList("cars","racing","history");
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 100, true,tags);
        tx.begin();
        em.persist(book);
        tx.commit();

        assertNotNull("ID should not be null", book.getId());
        assertEquals(3,book.getTags().size());
    }



    @Test
    public void createAddressTest() {
        Address address = new Address("111 Salo Way", "Leningrad", "VA", "ZIPCODE", "USA");
        tx.begin();
        em.persist(address);
        tx.commit();
        assertNotNull("ID should not be null", address.getId());
    }

    @Test
    public void createCustomerTest(){
        Customer customer = new Customer("John", "Smith", "jsmith@gmail.com",
                "1234565", new Date(), new Date());
        Address address = new Address("111 Salo Way", "Leningrad", "VA", "ZIPCODE", "USA");
        customer.setAddress(address);
        tx.begin();
        em.persist(address);
        em.persist(customer);
        tx.commit();
        assertNotNull("ID should not be null", customer.getId());
    }

    @Test
    public void createACustomerWithAnEmbeddedAddressTest() throws Exception {

        CustomerWithEmbedAddr customer = new CustomerWithEmbedAddr("John", "Smith",
                "jsmith@gmail.com", "1234565", new Date(), new Date());
        AddressEmbed address = new AddressEmbed("111 Salo Way", "VA", "Leningrad",
                "ZIPCODE", "USA");
        customer.setAddress(address);
        tx.begin();
        em.persist(customer);
        tx.commit();



        assertNotNull("ID should not be null", customer.getId());
    }


    @Test
    public void createCustomerWithOneAddressTest() throws Exception {

        Customer customer = new Customer("John", "Smith",
                "jsmith@gmail.com", "1234565", new Date(), new Date());
        Address address = new Address("111 Salo Way", "VA", "Leningrad",
                "ZIPCODE", "USA");
        customer.setAddress(address);

        tx.begin();
        em.persist(address);
        em.persist(customer);
        tx.commit();

        assertNotNull("ID should not be null", customer.getId());

        customer = em.find(Customer.class, customer.getId());
        assertNotNull("Address should not be null", customer.getAddress());
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
    public void createNewsWithCommentsTest() throws Exception {

        News news = new News("Jackson is Dead","EN","Death of Michael Jackson");
        Comment comment = new Comment("gonzo", "Third comment", 1, "2009-07-03");
        news.addComment(comment);

        Comment comment1 = new Comment("elvis", "First comment", 3, "2009-07-01");
        news.addComment(comment1);

        Comment comment2 = new Comment("gonzo", "Second comment", 5, "2009-07-02");
        news.addComment(comment2);

        Comment comment3 = new Comment("elvis", "Fourth comment", 2, "2009-07-06");
        news.addComment(comment3);

        tx.begin();
        em.persist(comment);
        em.persist(comment1);
        em.persist(comment2);
        em.persist(comment3);

        em.persist(news);
        tx.commit();

        tx.begin();
        news = em.find(News.class, new NewsId(news.getTitle(),news.getLanguage()));

        // Without the refresh the test will not work
        // The OrderBy annotation specifies the ordering of the elements of a collection valued association at the point when the association is retrieved."
        // The key here is the phrase "when the association is retrieved".  In this case, when your find is executed, the association is still managed and no retrieval logic occurs.
        em.refresh(news);

        tx.commit();

        assertEquals("Death of Michael Jackson", news.getContent());
        assertEquals(4, news.getComments().size());
        assertEquals("Fourth comment", news.getComments().get(0).getContent());
        assertEquals("Third comment", news.getComments().get(1).getContent());
        assertEquals("Second comment", news.getComments().get(2).getContent());
        assertEquals("First comment", news.getComments().get(3).getContent());
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
    public void createCreditCardTest(){
        CreditCard creditCard = new CreditCard("12345657657","10/12/2020",123,CreditCardType.MASTERCARD);
        tx.begin();
        em.persist(creditCard);
        tx.commit();
        assertNotNull("ID should not be null", creditCard.getNumber());

        creditCard = em.find(CreditCard.class,"12345657657" );
        assertEquals("MASTERCARD",creditCard.getCreditCardType().name());
    }

    @Test
    public void createCDtest(){
        HashMap<Integer, String> tracks = new HashMap<>();
        tracks.put(1, "Mutter");
        tracks.put(2, "Sonne");
        tracks.put(3, "Ich Will");
        CD cd = new CD("Mutter",12.0f,"Top-selling Rammstein album",tracks);

        tx.begin();
        em.persist(cd);
        tx.commit();

        assertNotNull("ID should not be null", cd.getId());
        assertEquals(3,cd.getTracks().size());

    }


    @Test
    public void createAnOrderWithOrderLinesTest() throws Exception {

        Order order = new Order();
        OrderLine line1 = new OrderLine("Mutter", 12d, 1);
        OrderLine line2 = new OrderLine("Rosenrot", 14.5d, 2);
        List<OrderLine> orderLines = Arrays.asList(line1,line2);
        order.setOrderLines(orderLines);

        tx.begin();
        em.persist(order);
        em.persist(line1);
        em.persist(line2);
        tx.commit();

        assertNotNull("Order ID should not be null", order.getId());
        assertNotNull("OL1 ID should not be null", line1.getId());
        assertNotNull("OL1 ID should not be null", line2.getId());

        order = em.find(Order.class, order.getId());
        assertNotNull("OrderLines should not be null", order.getOrderLines());
        assertEquals("Should have 2 order lines", order.getOrderLines().size(), 2);
    }


    @Test
    public void createDsAndArtists() throws Exception {

        Artist till = new Artist("Till", "Lindemann");
        Artist paul = new Artist("Paul", "Landers");
        Artist richard = new Artist("Richard", "Kruspe");
        Artist oliver = new Artist("Oliver", "Riedel");
        Artist flake = new Artist("Christian", "Lorenz");
        Artist doom = new Artist("Christian","Schneider");

        CD mutter = new CD("Mutter", 12.5F, "Released in 2001, best-selling studio album");
        CD rosenrot = new CD("Rosenrot", 18.5F, "Released in 2005 after Reise Reise");
        CD herzelied = new CD("Herzelied", 32F, "First album. Released in 1995");

        till.appearsOn(mutter);
        paul.appearsOn(mutter);
        richard.appearsOn(mutter);
        oliver.appearsOn(mutter);
        flake.appearsOn(mutter);
        doom.appearsOn(mutter);

        till.appearsOn(rosenrot);
        paul.appearsOn(rosenrot);
        richard.appearsOn(rosenrot);
        oliver.appearsOn(rosenrot);
        flake.appearsOn(rosenrot);
        doom.appearsOn(rosenrot);


        till.appearsOn(herzelied);
        paul.appearsOn(herzelied);
        richard.appearsOn(herzelied);
        oliver.appearsOn(herzelied);
        flake.appearsOn(herzelied);
        doom.appearsOn(herzelied);

        tx.begin();
        em.persist(till);
        em.persist(paul);
        em.persist(richard);
        em.persist(oliver);
        em.persist(flake);
        em.persist(doom);

        em.persist(mutter);
        em.persist(rosenrot);
        em.persist(herzelied);
        tx.commit();

        assertNotNull("Till ID should not be null", till.getId());
        assertNotNull("Paul ID should not be null", paul.getId());
        assertNotNull("Richard ID should not be null", richard.getId());
        assertNotNull("Oliver ID should not be null", oliver.getId());
        assertNotNull("Flake ID should not be null", flake.getId());
        assertNotNull("Doom ID should not be null", doom.getId());

        assertNotNull("Mutter ID should not be null", mutter.getId());
        assertNotNull("Rosenrot ID should not be null", rosenrot.getId());
        assertNotNull("Herzelied ID should not be null", herzelied.getId());

    }

    @Test
    public void createSeveralItemsTest() throws Exception {

        CD cd01 = new CD("St Pepper", 12.80f, "Beatles master piece",
                "Apple", 1, 53.32f, "Pop/Rock");
        CD cd02 = new CD("Love SUpreme", 20f, "John Coltrane love moment",
                "Blue Note", 2, 87.45f, "Jazz");

        Book book01 = new Book("Cars", 21f, "Book of cars", "123-456-789",
                 321,"Pinguin", false);
        Book book02 = new Book("F1 history", 37.5f, "F1 from 1950 to 2017", "0-553-29949-2 ",
                 264,"Foundation", true);

        tx.begin();
        em.persist(cd01);
        em.persist(cd02);
        em.persist(book01);
        em.persist(book02);
        tx.commit();

        assertNotNull("CD1 ID should not be null", cd01.getId());
        assertNotNull("CD2 ID should not be null", cd02.getId());
        assertNotNull("Book1 ID should not be null", book01.getId());
        assertNotNull("Book2 ID should not be null", book02.getId());
    }


}
