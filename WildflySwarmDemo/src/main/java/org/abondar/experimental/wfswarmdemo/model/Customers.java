package org.abondar.experimental.wfswarmdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonRootName("customers")
public class Customers extends ArrayList<Customer> {

    public Customers() {
        super();
    }


    public Customers(Collection<? extends Customer> c) {
        super(c);
    }

    @JsonProperty("customerList")
    public List<Customer> getCustomers() {
        return this;
    }
}
