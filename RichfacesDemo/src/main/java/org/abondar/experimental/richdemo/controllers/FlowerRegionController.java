package org.abondar.experimental.richdemo.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("flowerRegionController")
@RequestScoped
public class FlowerRegionController {
    private String type;
    private String size;
    private String vase;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getVase() {
        return vase;
    }

    public void setVase(String vase) {
        this.vase = vase;
    }
}
