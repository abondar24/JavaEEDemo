package org.abondar.experimental.javaeedemo.beanvalidation.test;

import org.abondar.experimental.javaeedemo.beanvalidation.Address;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AddressTest {

    @Test
    public void raiseConstraintViolationInvalidZipCode(){
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        Address address = new Address("111 Salo Way","Leningrad","VA","ZIPCODE","USA");

        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        assertEquals(1,violations.size());

        vf.close();
    }
}
