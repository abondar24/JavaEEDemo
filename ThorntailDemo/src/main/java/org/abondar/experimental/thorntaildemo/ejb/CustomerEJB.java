package org.abondar.experimental.thorntaildemo.ejb;


import org.abondar.experimental.thorntaildemo.model.Customer;
import org.abondar.experimental.thorntaildemo.model.Customers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CustomerEJB {

    @PersistenceContext(unitName = "demo_unit")
    private EntityManager em;


    public Long createCustomer(Customer customer){
        em.persist(customer);
        return customer.getId();

    }

    public Customers getCustomers(){
        var query = em.createNamedQuery("findCustomers",Customer.class);
        return new Customers(query.getResultList());
    }


    public Customer getCustomerById(Long id) {
        var query = em.createQuery("select c from Customer c where c.id=:id", Customer.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException ex){
            return new Customer();
        }
    }


    public Customers getCustomersByZipcode(String zip, String city) {

        var query = em.createQuery("select c from Customer c where" +
                " c.zipcode=:zip and c.city=:city", Customer.class);
        query.setParameter("zip", zip);
        query.setParameter("city", city);

        return new Customers(query.getResultList());
    }

    public Customers getCustomersByZipcode(String zip) {

        var query = em.createQuery("select c from Customer c where" +
                " c.zipcode=:zip ", Customer.class);
        query.setParameter("zip", zip);

        return new Customers(query.getResultList());
    }


    public Customer getCustomersByName(String firstName, String lastName) {

        var query = em.createQuery("select c from Customer c where" +
                " c.firstName=:firstName and c.lastName=:lastName", Customer.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);

        return query.getResultList().get(0);
    }


    public void deleteCustomer( Long customerId) {
        em.remove(em.find(Customer.class, customerId));

    }


    public void clearCustomers() {
        em.createNativeQuery("delete from Customer");

    }
}
