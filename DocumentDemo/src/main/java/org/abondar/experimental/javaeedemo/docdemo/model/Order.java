package org.abondar.experimental.javaeedemo.docdemo.model;

import java.util.Date;

public class Order {

    private Date date;
    private Integer id;

    public Order(Date date, Integer id) {
        this.date = date;
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "date=" + date +
                ", id=" + id +
                '}';
    }
}
