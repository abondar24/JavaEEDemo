package org.abondar.experimental.javaeedemo.ormdemo.listeners;

import org.abondar.experimental.javaeedemo.ormdemo.model.Customer;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class DataValidationListener {
    @PrePersist
    @PreUpdate
    private void validate(Customer customer)  {
        if (customer.getFirstName()==null || "".equals(customer.getFirstName()))
            throw new IllegalArgumentException("Invalid first name");

        if (customer.getLastName()==null || "".equals(customer.getLastName()))
            throw new IllegalArgumentException("Invalid last name");

    }

}
