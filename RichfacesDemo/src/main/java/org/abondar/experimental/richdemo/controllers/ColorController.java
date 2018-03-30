package org.abondar.experimental.richdemo.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("colorController")
@RequestScoped
public class ColorController {
    private String color="red";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
