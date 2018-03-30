package org.abondar.experimental.richdemo.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("registrationController")
@RequestScoped
public class RegistrationController {

    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
