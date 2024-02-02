package edu.neu.csye6220.fooddeliveryapplication.dao;

import edu.neu.csye6220.fooddeliveryapplication.dao.model.Cart;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.CartItem;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class CartDAO extends DAO {

    public CartDAO() {

    }

    public List<CartItem> get(long customerCartId) throws Exception {
        try {
            Query<CartItem> q = getSession().createQuery("from CartItem where customerCartId = :customerCartId");
            q.setParameter("customerCartId", customerCartId);
            return   q.list();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get cart items ", e);
        }
    }

    public CartItem getByMenuUser(long itemId, long customerCartId) throws Exception {
        try {
            Query<CartItem> q = getSession().createQuery("from CartItem where itemId = :itemId and customerCartId = :customerCartId");
            q.setParameter("itemId", itemId);
            q.setParameter("customerCartId", customerCartId);
            return q.uniqueResult();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get cart items ", e);
        }
    }


    public List<CartItem> getByMenu(long itemId) throws Exception {
        try {
            Query<CartItem> q = getSession().createQuery("from CartItem where itemId = :itemId");
            q.setParameter("itemId", itemId);
            return q.list();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get cart items ", e);
        }
    }



    public Cart getCart(long cartId) throws Exception {
        try {
            Query<Cart> q = getSession().createQuery("from Cart where cartId = :cartId");
            q.setParameter("cartId", cartId);
            return  (Cart) q.uniqueResult();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get cart ", e);
        }
    }

    public List<Cart> getAllCart() throws Exception {
        try {
            Query<Cart> q = getSession().createQuery("from Cart");
            return q.list();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get cart ", e);
        }
    }

    public CartItem updateCartItem(CartItem c, int quantity) throws Exception {
        try {
            begin();
            c.setQuantity(quantity);
            getSession().update(c);
            commit();
            return c;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get cart ", e);
        }
    }

    public CartItem getCartItem(long cartItemId) throws Exception {
        try {
            Query<CartItem> q = getSession().createQuery("from CartItem where cartItemId = :cartItemId");
            q.setParameter("cartItemId", cartItemId);
            return q.uniqueResult();

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while geting cartItem: " + e.getMessage());
        }
    }



    public CartItem create(CartItem c) throws Exception {
        try {
            begin();
            getSession().save(c);
            commit();
            return c;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating cartItem: " + e.getMessage());
        }
    }

    public Cart createCart(Cart c) throws Exception {
        try {
            begin();
            getSession().save(c);
            commit();
            return c;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating cart: " + e.getMessage());
        }
    }

    public void delete(CartItem c) throws Exception {
        try {
            begin();
            getSession().delete(c);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while deleting cart item " + e.getMessage());
        }
    }


    public void deleteCartItem(CartItem c) throws Exception {
        try {
            begin();
            getSession().delete(c);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while deleting cart item " + e.getMessage());
        }
    }


    public Cart updateCart(Cart m, int quantity, double price) throws Exception {
        try {
            begin();
            m.setTotalQuantity(quantity);
            m.setTotalPrice(price);
            getSession().update(m);
            commit();
            return m;


        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while updating cart item: " + e.getMessage());
        }

    }

}