package org.abondar.experimental.richdemo.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;

@Named("clockController")
@RequestScoped
public class ClockController {

    private boolean enabled;

    public Date getNow(){
        return new Date();
    }

    public void startClock(){
        enabled=true;
    }

    public void stopClock(){
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
