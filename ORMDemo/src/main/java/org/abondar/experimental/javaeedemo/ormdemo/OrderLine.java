package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order__line")
public class OrderLine {

    @Id
    @GeneratedValue
    private Long id;

    private String item;

    private Double unitPrice;

    private Integer quantity;

    public OrderLine() {
    }

    public OrderLine(String item, Double unitPrice, Integer quantity) {
        this.item = item;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
