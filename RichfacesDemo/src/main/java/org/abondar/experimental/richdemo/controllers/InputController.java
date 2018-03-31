package org.abondar.experimental.richdemo.controllers;

import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import javax.enterprise.context.RequestScoped;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Named("inputController")
@RequestScoped
public class InputController {

    private String email;
    private String name;
    private List<String> fruits  = Arrays.asList("Banana", "Strawberry", "Cranberry", "Orange");
    private List<String> vegetables  = Arrays.asList("Potatoes", "Broccoli", "Tomato", "Carrot");
    private List<Integer> numberOfItems  = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    private List<SelectItem> firstList = new ArrayList<>();
    private List<SelectItem> secondList = new ArrayList<>();
    private String currentType;
    private Date today;


    public InputController(){
        SelectItem item = new SelectItem("fruits","Fruits");
        firstList.add(item);

        item = new SelectItem("vegetables","Vegetables");
        firstList.add(item);

        for (String f: fruits){
            item = new SelectItem(f);
        }
    }

    public void valueChanged(ValueChangeEvent event){
        secondList.clear();
        if (event.getNewValue()!=null){
            List<String> currentItems;
            if (event.getNewValue().equals("fruits")){
                currentItems = fruits;
            } else {
                currentItems = vegetables;
            }

            currentItems.forEach(ci->{
                SelectItem item = new SelectItem(ci);
                secondList.add(item);
            });
        }
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFruits() {
        return fruits;
    }

    public void setFruits(List<String> fruits) {
        this.fruits = fruits;
    }



    public List<SelectItem> getFirstList() {
        return firstList;
    }

    public void setFirstList(List<SelectItem> firstList) {
        this.firstList = firstList;
    }

    public List<SelectItem> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<SelectItem> secondList) {
        this.secondList = secondList;
    }



    public String getCurrentType() {
        return currentType;
    }

    public void setCurrentType(String currentType) {
        this.currentType = currentType;
    }

    public List<Integer> getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(List<Integer> numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }
}
