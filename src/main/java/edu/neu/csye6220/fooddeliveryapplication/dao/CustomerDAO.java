package edu.neu.csye6220.fooddeliveryapplication.dao;

import edu.neu.csye6220.fooddeliveryapplication.dao.model.Customer;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAO  extends DAO {

    public CustomerDAO() {

    }

    public List<Customer> get() throws Exception {
        try {
            begin();
            Query<Customer> q = getSession().createQuery("from Customer");
            List<Customer> customers = q.list();
            commit();
            return customers;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get customer", e);
        }
    }

    public Customer register(Customer r) throws Exception {
        try {
            begin();
            getSession().save(r);
            commit();
            return r;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating customer: " + e.getMessage());
        }
    }

    public Customer get(long userId) throws Exception {
        try {
            begin();
            Query q = getSession().createQuery("from User where userId = :userId");
            q.setParameter("userId", userId);
            Customer r = (Customer) q.uniqueResult();
            commit();
            return r;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get customer " + userId, e);
        }
    }
}