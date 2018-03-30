package org.abondar.experimental.richdemo.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;

@Named("queueController")
@RequestScoped
public class QueueController {

    public Date getNow1(){
        return new Date();
    }

    public Date getNow2(){
        return new Date();
    }

    public void action1(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void action2(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
