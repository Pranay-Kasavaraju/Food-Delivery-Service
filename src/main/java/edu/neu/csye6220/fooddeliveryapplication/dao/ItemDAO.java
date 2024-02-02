package edu.neu.csye6220.fooddeliveryapplication.dao;

import edu.neu.csye6220.fooddeliveryapplication.dao.model.Item;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDAO extends DAO{
    public ItemDAO() {

    }

    public List<Item> getList(long resId) throws Exception {
        try {
            Query<Item> q = getSession().createQuery("from Item where resId = :resId");
            q.setParameter("resId", resId);
            return q.list();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get item", e);
        }
    }



    public Item getItem(long itemId) throws Exception {
        try {
            Query<Item> q = getSession().createQuery("from Item where itemId = :itemId");
            q.setParameter("itemId", itemId);
            Item m = q.uniqueResult();
            System.out.println("aaa");
            return m;
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get item", e);
        }
    }

    public Item create(Item item) throws Exception {
        try {
            begin();
            getSession().save(item);
            commit();
            return item;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating item: " + e.getMessage());
        }
    }

    public boolean updateItem(long itemId, String name, double price, String cuisine, String type) throws Exception {
        try {
            Query<Item> q = getSession().createQuery("from Item where itemId = :itemId");
            q.setParameter("itemId", itemId);
            Item item = q.uniqueResult();
            if(item!=null){
                item.setName(name);
                item.setPrice(price);
                item.setCuisine(cuisine);
                item.setType(type);
                begin();
                getSession().update(item);
                commit();
                return true;
            }else{
                return false;
            }


        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating item: " + e.getMessage());
        }

    }


    public void delete(Item item) throws Exception {
        try {
            begin();
            getSession().delete(item);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while deleting item " + e.getMessage());
        }
    }
}
