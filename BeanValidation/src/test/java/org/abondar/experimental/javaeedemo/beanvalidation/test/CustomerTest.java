package org.abondar.experimental.javaeedemo.beanvalidation.test;

import org.abondar.experimental.javaeedemo.beanvalidation.Customer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void init(){
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close(){
        vf.close();
    }

    @Test
    public void raiseNoConstraintViolationTest(){
        Customer customer = new Customer("Alex","Bondar","abondar@bsft.com");

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(0,violations.size());
    }

    @Test
    public void raiseConstraintViolantionInvalidEmailTest(){
        Customer customer = new Customer("Alex","Bondar","DummyEmail");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertEquals(1,violations.size());
        assertEquals("invalid email address",violations.iterator().next().getMessage());
        assertEquals("DummyEmail",violations.iterator().next().getInvalidValue());
        assertEquals("invalid email address",violations.iterator().next().getMessageTemplate());


    }

}
