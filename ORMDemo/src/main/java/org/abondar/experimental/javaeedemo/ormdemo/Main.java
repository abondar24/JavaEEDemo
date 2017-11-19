package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        Book book = new Book("Cars", 10.0f, "The book of cars", "1-84023-742-2", 300, true);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo_unit");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(book);
        em.createNamedQuery("findAllBooks", Book.class).getResultList().forEach(System.out::println);
        book = em.createNamedQuery("findBookCars", Book.class).getSingleResult();
        tx.commit();
        em.close();
        emf.close();
        System.out.println(book);

    }
}
