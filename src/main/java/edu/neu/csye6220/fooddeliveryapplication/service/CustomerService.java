package edu.neu.csye6220.fooddeliveryapplication.service;

import edu.neu.csye6220.fooddeliveryapplication.dao.RestaurantDAO;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
	
	private RestaurantDAO restaurantDao;
	
	public CustomerService(RestaurantDAO restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

	public List<Restaurant> getRestaurants() {
		return restaurantDao.getRestaurants();
	}	
}
