package org.abondar.experimental.richdemo.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named("dummyController")
@RequestScoped
public class DummyController {

    private String selection;
    private List<String> renderComponents;

    @PostConstruct
    public void init(){
          renderComponents = new ArrayList<>();
    }

    public void selectComponents(AjaxBehaviorEvent event){
        renderComponents.add(selection);
    }

    public Date getClock1(){
        return new Date();
    }

    public Date getClock2(){
        return new Date();
    }

    public Date getClock3(){
        return new Date();
    }

    public List<String> getRenderComponents() {
        return renderComponents;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
