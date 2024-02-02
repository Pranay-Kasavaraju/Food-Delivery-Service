package edu.neu.csye6220.fooddeliveryapplication.dao.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @Column
    private int quantity;


    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getItemId() {
        return orderItemId;
    }

    public void setItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}