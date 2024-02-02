package edu.neu.csye6220.fooddeliveryapplication.dao;

import edu.neu.csye6220.fooddeliveryapplication.dao.model.Restaurant;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantDAO extends DAO {


	public List<Restaurant> getList() {
		Query<Restaurant> query = getSession().createQuery("FROM Restaurant");
		return query.list();
	}

	public void save(Restaurant restaurant) throws Exception {
		try {
			begin();
			getSession().save(restaurant);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete the restaurant", e);
		}
	}

	public void saveAll(List<Restaurant> restaurants) throws Exception {
		try {
			begin();
			getSession().save(restaurants);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete the restaurants", e);
		}
	}

	public Restaurant getRestaurant(long id) {
		return getSession().get(Restaurant.class, id);
	}

	public void deleteRestaurant(Restaurant c) throws Exception {
		try {
			begin();
			getSession().delete(c);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete the category", e);
		}
	}

	public void updateRestaurant(Restaurant c) throws Exception {
		try {
			begin();
			getSession().update(c);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not delete the Restaurant", e);
		}
	}
	public List<Restaurant> getRestaurants() {
		Query<Restaurant> query = getSession().createQuery("FROM Restaurant");

		return query.list();
	}

}