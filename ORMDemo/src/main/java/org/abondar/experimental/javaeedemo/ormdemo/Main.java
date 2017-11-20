package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo_unit");

        PersistenceUtil.addAndFindBook(emf);
        Long customerId = PersistenceUtil.addAndFindCustomer(emf);
        PersistenceUtil.removeCustomer(emf, customerId);
        customerId = PersistenceUtil.addAndRefreshCustomer(emf);
        PersistenceUtil.emContainsCustomerEntity(emf, customerId);
        PersistenceUtil.addAndDetachCustomer(emf);
        PersistenceUtil.updateCustomer(emf,customerId);
        PersistenceUtil.addCustomerByCascade(emf);
        emf.close();
    }
}
