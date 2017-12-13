package org.abondar.experimental.javaeedemo.restdemo.config;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseProducer {


    @Produces
    @PersistenceContext(unitName = "demo_unit")
    private EntityManager em;
}