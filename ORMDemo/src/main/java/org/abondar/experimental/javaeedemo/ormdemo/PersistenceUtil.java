package org.abondar.experimental.javaeedemo.ormdemo;

import org.abondar.experimental.javaeedemo.ormdemo.model.Address;
import org.abondar.experimental.javaeedemo.ormdemo.model.Book;
import org.abondar.experimental.javaeedemo.ormdemo.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class PersistenceUtil {


    public static void addAndFindBook(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 300, true);

        tx.begin();
        em.persist(book);
        tx.commit();

        em.createNamedQuery("findAllBooks", Book.class).getResultList().forEach(System.out::println);
        book = em.createNamedQuery("findBookCars", Book.class).getSingleResult();
        em.close();


        System.out.println("Cars book: " + book);

    }

    public static Long addAndFindCustomer(EntityManagerFactory emf) {
        Customer customer = new Customer("Antony", "Balla", "tballa@mail.com",
                "49562323423423423432", new Date(), new Date());
        Address address = new Address("Ritherdon Rd", "", "London", "8QE", "UK");
        customer.setAddress(address);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(address);
        em.persist(customer);
        tx.commit();

        customer = em.find(Customer.class, customer.getId());
        System.out.println("Found customer: " + customer);
        em.close();

        return customer.getId();
    }


    public static void removeCustomer(EntityManagerFactory emf, Long customerId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Customer customer = em.find(Customer.class, customerId);
        tx.begin();
        em.remove(customer);
        tx.commit();


        System.out.println("Removed a customer with id: " + customerId);
    }


    public static Long addAndRefreshCustomer(EntityManagerFactory emf) {
        Customer customer = new Customer("Antony", "Balla", "tballa@mail.com",
                "49562323423423423432", new Date(), new Date());
        Address address = new Address("Ritherdon Rd", "", "London", "8QE", "UK");
        customer.setAddress(address);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(address);
        em.persist(customer);
        tx.commit();
        System.out.println("Added a customer: " + customer.toString());

        customer = em.find(Customer.class, customer.getId());
        System.out.println("Customer's name: " + customer.getFirstName());

        customer.setFirstName("Arsen");
        System.out.println("Changed name to: " + customer.getFirstName());
        em.refresh(customer);
        System.out.println("Customer's name after refresh: " + customer.getFirstName());

        em.close();

        return customer.getId();
    }

    public static void emContainsCustomerEntity(EntityManagerFactory emf, Long customerId) {
        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class, customerId);
        System.out.println("Customer's name: " + customer.getFirstName());

        System.out.println("Entity manager contains: " + em.contains(customer));
        em.close();

    }

    public static void addAndDetachCustomer(EntityManagerFactory emf) {
        Customer customer = new Customer("Anton", "Spak", "spak@mail.com",
                "49562", new Date(), new Date());
        Address address = new Address("111 Salo Way", "Leningrad", "VA", "ZIPCODE", "USA");

        customer.setAddress(address);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(address);
        em.persist(customer);
        tx.commit();
        System.out.println("Added a customer: " + customer.toString());

        em.detach(customer);
        System.out.println("Customer detached: " + !em.contains(customer));
        em.close();


    }


    public static void updateCustomer(EntityManagerFactory emf, Long customerId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Customer customer = em.find(Customer.class, customerId);
        customer.setPhoneNumber("44342423");


        tx.begin();
        em.merge(customer);
        tx.commit();


        customer = em.find(Customer.class, customerId);
        System.out.println("Updated customer: "+customer.toString());
        em.close();
    }


    public static void addCustomerByCascade(EntityManagerFactory emf) {
        Customer customer = new Customer("Arsen", "Ballayan", "raadsd@mail.com",
                "49343543534", new Date(), new Date());
        Address address = new Address("Ritherdon Rd", "", "London", "8QE", "UK");
        customer.setAddress(address);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(customer);
        tx.commit();

        System.out.println("Added customer: " + customer);
        em.close();
    }


}
