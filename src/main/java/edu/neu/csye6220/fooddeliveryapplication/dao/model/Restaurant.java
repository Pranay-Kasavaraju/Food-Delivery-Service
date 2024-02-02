package edu.neu.csye6220.fooddeliveryapplication.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "userId")
public class Restaurant extends User{

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="restaurant")
	private Set<Item> items = new HashSet<Item>();

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="restaurant")
	private Set<Order> orderSet = new HashSet<>();

	public Restaurant() {

	}

	public Set<Item> getItems() {
		return items;
	}



	public void setItems(Set<Item> menuSet) {
		this.items = items;
	}

	public Set<Order> getOrderSet() {
		return orderSet;
	}

	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

}
