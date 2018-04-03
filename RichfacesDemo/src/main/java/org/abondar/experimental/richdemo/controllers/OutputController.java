package org.abondar.experimental.richdemo.controllers;

import org.richfaces.event.PanelToggleEvent;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named("outputController")
@RequestScoped
public class OutputController implements Serializable {


    private Boolean open;

    private String tab = "t1";
    private String selectedTab = "t1";

    public void loadPanelData(PanelToggleEvent event){
        open = true;
    }

    public void changeTab(ValueChangeEvent event){
        selectedTab = (String) event.getNewValue();
    }


    public Date getNow(){
        return new Date();
    }


    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }
}
