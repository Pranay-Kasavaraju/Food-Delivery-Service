package edu.neu.csye6220.fooddeliveryapplication.dao.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
public class Customer extends User{

    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="customer")
    private Set<Order> orderSet = new HashSet<>();

    @OneToOne
    @PrimaryKeyJoinColumn
    private Cart cart;

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}