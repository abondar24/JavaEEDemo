package org.abondar.experimental.javaeedemo.restdemo.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Customer.class)
public class Customers extends ArrayList<Customer> {

    public Customers() {
        super();
    }


    public Customers(Collection<? extends Customer> c) {
        super(c);
    }

    @XmlElement(name = "customer")
    public List<Customer> getCustomers() {
        return this;
    }
}
