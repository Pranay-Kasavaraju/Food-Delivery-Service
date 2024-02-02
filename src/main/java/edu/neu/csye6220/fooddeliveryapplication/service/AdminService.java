package edu.neu.csye6220.fooddeliveryapplication.service;

import edu.neu.csye6220.fooddeliveryapplication.dao.RestaurantDAO;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

	private RestaurantDAO restaurantDao;
	
	public AdminService(RestaurantDAO restaurantDao) {
		this.restaurantDao = restaurantDao;
	}

}
