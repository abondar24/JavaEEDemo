package org.abondar.experimental.richdemo.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("statusController")
@RequestScoped
public class StatusController {

    private String text;

    public void upperCase(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        setText(text.toUpperCase());
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
