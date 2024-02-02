package edu.neu.csye6220.fooddeliveryapplication.controller;

import edu.neu.csye6220.fooddeliveryapplication.dao.CustomerDAO;
import edu.neu.csye6220.fooddeliveryapplication.dao.RestaurantDAO;
import edu.neu.csye6220.fooddeliveryapplication.dao.UserDAO;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping(value = "/create.htm")
    public String create(HttpServletRequest request, UserDAO userDao, RestaurantDAO restaurantDao, CustomerDAO customerDao, ModelMap map) {
        String useremail = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        User user = createUser(useremail, password, role, name, phoneNumber, address, city);

        try {
            String userCreateForm = createUser(userDao, restaurantDao, customerDao, map, useremail, password, role, name, phoneNumber, address, city, user);
            if (userCreateForm != null) return userCreateForm;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "userCreated";
    }

    @GetMapping(value = "/login.htm")
    public String getLogin() {

        return "userLogin";
    }

    @PostMapping(value = "/login.htm")
    public String postLogin(HttpServletRequest request, UserDAO userDao, ModelMap map) {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User u = userDao.get(username, password);

            if (u != null && u.getRole().equals("Customer")) {
                session.setAttribute("user", u);
                map.addAttribute("user", u);
                return "customerHome";
            }
            if (u != null && u.getRole().equals("Restaurant")) {
                session.setAttribute("user", u);
                map.addAttribute("user", u);
                return "restaurantHome";
            }
            else {
                map.addAttribute("errorMessage", "Invalid username/password!");
                return "error";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    @GetMapping(value = "/create.htm")
    public String showCreateForm() {

        return "userCreateForm";
    }

    private static String createUser(UserDAO userDao, RestaurantDAO restaurantDao, CustomerDAO customerDao, ModelMap map, String useremail, String password, String role, String name, String phoneNumber, String address, String city, User user) throws Exception {
        try {
            List<User> userList = userDao.getList();
            for(User u : userList) {
                if(user.getUserEmail().equals(u.getUserEmail())) {
                    map.addAttribute("errorMessage", "User email already registered!");
                    return "userCreateForm";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(user.getRole().equals("Restaurant")) {
            Restaurant restaurant = createRestaurant(useremail, password, role, name, phoneNumber, address, city);


            restaurantDao.save(restaurant);
        }

        if(user.getRole().equals("Customer")) {
            Customer customer = createCustomer(useremail, password, role, name, phoneNumber, address, city);


            Cart c = new Cart();
            customer.setCart(c);
            c.setCustomer(customer);

            customerDao.register(customer);
        }
        return null;
    }

    private static Customer createCustomer(String useremail, String password, String role, String name, String phoneNumber, String address, String city) {
        Customer customer = new Customer();
        customer.setAddress(address);
        customer.setCity(city);
        customer.setName(name);
        customer.setPassword(password);
        customer.setPhoneNumber(phoneNumber);
        customer.setRole(role);
        customer.setUserEmail(useremail);
        customer.setOrderSet(new HashSet<>());
        return customer;
    }

    private static Restaurant createRestaurant(String useremail, String password, String role, String name, String phoneNumber, String address, String city) {
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setCity(city);
        restaurant.setName(name);
        restaurant.setPassword(password);
        restaurant.setPhoneNumber(phoneNumber);
        restaurant.setRole(role);
        restaurant.setUserEmail(useremail);
        restaurant.setItems(new HashSet<>());
        restaurant.setOrderSet(new HashSet<>());
        return restaurant;
    }

    private static User createUser(String useremail, String password, String role, String name, String phoneNumber, String address, String city) {
        User user = new User();
        user.setUserEmail(useremail);
        user.setPassword(password);
        user.setRole(role);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setCity(city);
        return user;
    }

}