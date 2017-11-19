package org.abondar.experimental.javaeedemo.ormdemo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="cust_order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="cust_order_fk")
    private List<OrderLine> orderLines;

    public Order(){
        this.creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", orderLines=" + orderLines +
                '}';
    }
}
