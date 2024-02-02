package edu.neu.csye6220.fooddeliveryapplication.dao.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "customer"))
    private long cartId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Customer customer;

    @Column
    private int totalQuantity;


    @Column
    private double totalPrice;

    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="cart")
    private Set<CartItem> itemSet = new HashSet<CartItem>();



    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public Set<CartItem> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<CartItem> itemSet) {
        this.itemSet = itemSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}