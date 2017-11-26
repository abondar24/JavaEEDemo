package org.abondar.experimental.javaeedemo.docdemo.model;

public class OrderLine {
    private String item;
    private Double unitPrice;
    private Integer quantity;

    public OrderLine(String item, Double unitPrice, Integer quantity) {
        this.item = item;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderLine(){}

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
                "item='" + item + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
