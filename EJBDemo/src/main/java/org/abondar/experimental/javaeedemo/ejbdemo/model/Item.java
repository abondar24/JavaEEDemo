package org.abondar.experimental.javaeedemo.ejbdemo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Item {

    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    @Column(name="title",nullable = false,updatable = false)
    protected String title;

    protected Float price;

    @Size(min = 10,max=2000)
    @Column(length = 2000)
    protected String description;

    public Item(){}

    public Item(String title, Float price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
