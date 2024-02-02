package edu.neu.csye6220.fooddeliveryapplication.dao.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long itemId;
	@Column
	String name;
	@Column
	double price;
	@Column
	String cuisine;
	@Column
	String type;

	@ManyToOne
	@JoinColumn(name = "resId")
	private Restaurant restaurant;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="item")
	private Set<CartItem> cartItemSet = new HashSet<>();

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="item")
	private Set<OrderItem> orderItemSet = new HashSet<>();

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<CartItem> getCartItemSet() {
		return cartItemSet;
	}

	public void setCartItemSet(Set<CartItem> cartItemSet) {
		this.cartItemSet = cartItemSet;
	}

	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
	}
}
