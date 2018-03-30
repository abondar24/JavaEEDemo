package org.abondar.experimental.richdemo.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("cmdController")
@RequestScoped
public class CommandController {
    private String name="";
    private Integer count=0;


    public void doCount(){
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
