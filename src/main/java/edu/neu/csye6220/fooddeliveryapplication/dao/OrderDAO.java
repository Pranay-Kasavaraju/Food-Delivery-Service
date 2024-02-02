package edu.neu.csye6220.fooddeliveryapplication.dao;

import edu.neu.csye6220.fooddeliveryapplication.dao.model.Order;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.OrderItem;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO extends DAO {
    public List<Order> getOrder(long customerId) throws Exception {
        try {
            Query<Order> q = getSession().createQuery("from Order where customerId = :customerId");
            q.setParameter("customerId", customerId);
            List<Order> m = (List<Order>) q.list();
            return m;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while geting order: " + e.getMessage());
        }
    }


    public List<Order> getOrderByRes(long restaurantId) throws Exception {
        try {
            Query<Order> q = getSession().createQuery("from Order where restaurantId = :restaurantId");
            q.setParameter("restaurantId", restaurantId);
            List<Order> m = (List<Order>) q.list();
            return m;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while geting order: " + e.getMessage());
        }
    }


    public Order getOrderById(long orderId) throws Exception {
        try {
            Query<Order> q = getSession().createQuery("from Order where orderId = :orderId");
            q.setParameter("orderId", orderId);
            Order m = (Order) q.uniqueResult();
            return m;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while geting order: " + e.getMessage());
        }
    }


    public List<OrderItem> getOrderItemList(long orderId) throws Exception {
        try {
            Query<OrderItem> q = getSession().createQuery("from OrderItem where orderId = :orderId");
            q.setParameter("orderId", orderId);
            List<OrderItem> m = (List<OrderItem>) q.list();
            return m;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while geting order item: " + e.getMessage());
        }
    }


    public OrderItem create(OrderItem c) throws Exception {
        try {
            begin();
            getSession().save(c);
            commit();
            return c;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating orderItem: " + e.getMessage());
        }
    }

    public Order createOrder(Order c) throws Exception {
        try {
            begin();
            getSession().save(c);
            commit();
            return c;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating order: " + e.getMessage());
        }
    }

    public OrderItem update(OrderItem c, Order order) throws Exception {
        try {
            begin();
            c.setOrder(order);
            getSession().update(c);
            commit();
            return c;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while updating orderItem: " + e.getMessage());
        }
    }


    public void updateOrder(Order order) throws Exception {
        try {
            begin();
            order.setOrderStatus("Customer Received Food");
            getSession().update(order);
            commit();

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while updating order: " + e.getMessage());
        }
    }

    public void ResupdateOrder(Order order) throws Exception {
        try {
            begin();
            order.setOrderStatus("Food under delivering");
            getSession().update(order);
            commit();

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while updating order: " + e.getMessage());
        }
    }
}