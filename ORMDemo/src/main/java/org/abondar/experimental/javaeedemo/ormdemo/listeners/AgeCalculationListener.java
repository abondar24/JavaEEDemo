package org.abondar.experimental.javaeedemo.ormdemo.listeners;

import org.abondar.experimental.javaeedemo.ormdemo.model.Customer;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AgeCalculationListener {
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateAge(Customer customer){
        if (customer.getDateOfBirth()==null){
           customer.setAge(null);
            return;

        }

        Calendar birth = new GregorianCalendar();
        birth.setTime(customer.getDateOfBirth());

        Calendar now = new GregorianCalendar();
        now.setTime(new Date());

        int adjust = 0;
        if (now.get(Calendar.DAY_OF_YEAR) - birth.get(Calendar.DAY_OF_YEAR)<0){
            adjust = -1;
        }
        customer.setAge(now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust);

    }

}
