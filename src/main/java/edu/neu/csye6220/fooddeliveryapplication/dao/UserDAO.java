package edu.neu.csye6220.fooddeliveryapplication.dao;

import edu.neu.csye6220.fooddeliveryapplication.dao.model.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends DAO {

    public UserDAO() {
    }

    public User get(String userEmail, String password) throws Exception {
        try {
            Query<User> q = getSession().createQuery("from User where userEmail = :useremail and password = :password");
            q.setParameter("useremail", userEmail);
            q.setParameter("password", password);
            return (User) q.uniqueResult();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get user " + userEmail, e);
        }
    }

    public List<User> getList() throws Exception {
        try {
            Query<User> q = getSession().createQuery("from User");
            return q.list();
        } catch (HibernateException e) {
            rollback();
            throw new Exception("Could not get user ", e);
        }
    }

    public User get(String userEmail){
        try {
            Query<User> q = getSession().createQuery("from User where userEmail = :useremail");
            q.setParameter("useremail", userEmail);
            return q.uniqueResult();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;

    }


    public User save(User u) throws Exception {
        try {
            begin();
            getSession().save(u);
            commit();
            return u;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating user: " + e.getMessage());
        }
    }


    public User update(long userId, String address, String city, String phoneNumber, String name) throws Exception {
        try {
            begin();
            Query<User> q = getSession().createQuery("from User where userId = :userId");
            q.setParameter("userId", userId);
            User r = q.uniqueResult();
            r.setAddress(address);
            r.setCity(city);
            r.setName(name);
            r.setPhoneNumber(phoneNumber);
            getSession().update(r);
            commit();
            return r;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while updating user info: " + e.getMessage());
        }
    }
}
