package edu.neu.csye6220.fooddeliveryapplication.dao.model;

import javax.persistence.*;

@Entity
public class CartItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartItemId;



    @ManyToOne
    @JoinColumn(name = "customerCartId")
    private Cart cart;


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

    public long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}