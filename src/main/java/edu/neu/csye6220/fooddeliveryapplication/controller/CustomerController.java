package edu.neu.csye6220.fooddeliveryapplication.controller;

import edu.neu.csye6220.fooddeliveryapplication.dao.*;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.*;
import edu.neu.csye6220.fooddeliveryapplication.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class CustomerController {

	private CustomerService customerService;
	
	public CustomerController(CustomerService orderService) {
		this.customerService = orderService;
	}

	@GetMapping(value = "/myOrders.htm")
	public String showMyOrders(HttpServletRequest request, OrderDAO orderDao) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			List<Order> orders = orderDao.getOrder(u.getUserId());

			request.getSession().setAttribute("orders", orders);
			return "orderList";


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/orderDetails.htm")
	public String showMyOrderDetail(HttpServletRequest request, RestaurantDAO restaurantDao, OrderDAO orderDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDao.getOrderById(Long.parseLong(orderId));
			request.getSession().setAttribute("order", order);

			List<OrderItem> orderItems = orderDao.getOrderItemList(Long.parseLong(orderId));

			request.getSession().setAttribute("orderItems", orderItems);
			return "orderDetails";


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/restaurants.htm")
	public String getRestaurants(HttpServletRequest request){

		try {
			List<Restaurant> restaurants = customerService.getRestaurants();
			request.getSession().setAttribute("restaurants", restaurants);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "restaurantsList";
	}

	@GetMapping("/menu.htm")
	public String getMenu(HttpServletRequest request, RestaurantDAO restaurantDao) {

		try {
			String id = request.getParameter("restaurantId");

			Restaurant restaurant = restaurantDao.getRestaurant(Long.parseLong(id));
			Set<Item> items = restaurant.getItems();

			request.getSession().setAttribute("items", items);
			request.getSession().setAttribute("restaurant", restaurant);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "restaurantMenu";
	}

	@GetMapping(value = "/updateOrder.htm")
	public String updateOrder(HttpServletRequest request, OrderDAO orderDao) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDao.getOrderById(Long.parseLong(orderId));
			orderDao.updateOrder(order);
			request.getSession().setAttribute("result", "Updated Successfully");

			return "customerSuccess";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getCartObject(HttpServletRequest request, CartDAO cartDao, CustomerDAO customerDao) throws Exception {
		User u = (User) request.getSession().getAttribute("user");
		Customer c = customerDao.get(u.getUserId());
		int totalQuantity=0;
		double totalPrice=0;

		if(c.getCart() != null) {
			List<CartItem> itemSet = cartDao.get(u.getUserId());
			Cart cart = cartDao.getCart(u.getUserId());
			request.getSession().setAttribute("itemSet", itemSet);
			Set<Restaurant> resSet = new HashSet<>();
			for(CartItem item : itemSet) {
				resSet.add(item.getItem().getRestaurant());
				totalQuantity+=item.getQuantity();
				totalPrice+=item.getQuantity()*item.getItem().getPrice();
			}
			Map<Restaurant, Set<CartItem>> itemMap = new HashMap<>();

			for(Restaurant r : resSet) {
				Set<CartItem> list = new HashSet<>();
				for(CartItem cartItem : itemSet) {
					Restaurant res = cartItem.getItem().getRestaurant();
					if(res == r) {
						list.add(cartItem);
					}
				}
				itemMap.put(r, list);

			}
			request.getSession().setAttribute("cart", cart);
			request.getSession().setAttribute("itemMap", itemMap);

		}
		else {
			request.getSession().setAttribute("itemSet", new HashSet<CartItem>());
			request.getSession().setAttribute("cart", new Cart());
		}

		request.getSession().setAttribute("totalQuantity", totalQuantity);
		request.getSession().setAttribute("totalPrice", totalPrice);
		return "viewCart";
	}

	@GetMapping(value = "/addToCart.htm")
	public String addCartItems(HttpServletRequest request, CartDAO cartDao, CustomerDAO customerDao, ItemDAO itemDAO) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			Customer c = customerDao.get(u.getUserId());
			String itemId = request.getParameter("itemId");
			String quantity = request.getParameter("quantity");
			Item item = itemDAO.getItem(Long.parseLong(itemId));

			return addCartItemObjects(request, cartDao, u, c, quantity, item);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	@RequestMapping(value = "/removeItem.htm", method = RequestMethod.GET)
	public String removeItem(HttpServletRequest request, RestaurantDAO restaurantDao, CartDAO cartDao, ItemDAO itemDAO, ModelMap map) {

		try {
			return removeCartItemObjects(request, cartDao, itemDAO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String removeCartItemObjects(HttpServletRequest request, CartDAO cartDao, ItemDAO itemDAO) throws Exception {
		String itemId = request.getParameter("itemId");
		CartItem cartItem = cartDao.getCartItem(Long.parseLong(itemId));
		if(cartItem != null) {
			Cart cart = cartDao.getCart(cartItem.getCart().getCartId());
			cartDao.updateCart(cart, cart.getTotalQuantity() - cartItem.getQuantity(), cart.getTotalPrice() - cartItem.getItem().getPrice() * cartItem.getQuantity());
			cart.getItemSet().remove(cartItem);
			cartItem.setCart(null);
			Item item = itemDAO.getItem(cartItem.getItem().getItemId());
			item.getCartItemSet().remove(cartItem);
			cartDao.delete(cartItem);

			request.getSession().setAttribute("result", "Removed Successfully");
			return "customerSuccess";
		}
		else {
			return "error";
		}
	}


	@RequestMapping(value = "/placeOrder.htm", method = RequestMethod.POST)
	public String placeOrder(HttpServletRequest request, RestaurantDAO restaurantDao, CartDAO cartDao, ItemDAO itemDAO, CustomerDAO customerDao, OrderDAO orderDao) {

		try {
			return addOrderObject(request, cartDao, itemDAO, customerDao, orderDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String addOrderObject(HttpServletRequest request, CartDAO cartDao, ItemDAO itemDAO, CustomerDAO customerDao, OrderDAO orderDao) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		Customer c = customerDao.get(user.getUserId());
		Map<Restaurant, Set<CartItem>> itemMap = (Map<Restaurant, Set<CartItem>>) request.getSession().getAttribute("itemMap");

		if(itemMap.size() == 0) {
			return "error";
		}
		else {
			Set<OrderItem> itemAddedSet = new HashSet<OrderItem>();
			boolean flag = false;
			for(Restaurant restaurant : itemMap.keySet()) {
				flag = false;
				Set<OrderItem> orderItems = new HashSet<OrderItem>();
				for(CartItem cartItem : itemMap.get(restaurant)) {
					OrderItem orderItem = new OrderItem();
					Item item = itemDAO.getItem(cartItem.getItem().getItemId());
						orderItem.setItem(item);
						orderItem.setOrder(null);
						orderItem.setQuantity(cartItem.getQuantity());
						flag = true;
						orderDao.create(orderItem);
						orderItems.add(orderItem);
						itemAddedSet.add(orderItem);
				}
				int quantity = 0;
				double price = 0;
				for(OrderItem orderItem : orderItems) {
					quantity += orderItem.getQuantity();
					price += orderItem.getQuantity() * orderItem.getItem().getPrice();
				}

				if(flag) {
					Order order = new Order();
					order.setCustomer(c);
					order.setOrderStatus("Processing");
					order.setRestaurant(restaurant);
					order.setItems(orderItems);
					order.setTotalQuantity(quantity);
					order.setTotalPrice(price);

					orderDao.createOrder(order);
					for(OrderItem orderItem : orderItems) {
						orderDao.update(orderItem, order);
					}
				}

			}
			List<CartItem> cartItems = cartDao.get(user.getUserId());
			for(CartItem cartItem : cartItems) {
				cartDao.delete(cartItem);
			}
			cartDao.updateCart(cartDao.getCart(user.getUserId()), 0, 0);
			cartDao.get(user.getUserId());
				request.getSession().setAttribute("result", "Order placed Successfully");
			return "customerSuccess";

		}
	}

	private static String addCartItemObjects(HttpServletRequest request, CartDAO cartDao, User u, Customer c, String quantity, Item item) throws Exception {
		CartItem i = cartDao.getByMenuUser(item.getItemId(), u.getUserId());
		if(i == null) {
			Cart cart = cartDao.getCart(u.getUserId());
			if(cart == null){
				cart = new Cart();
				cart.setCustomer(c);
				cart.setTotalPrice(0);
				cart.setTotalQuantity(0);
				cartDao.createCart(cart);
			}
			CartItem cartItem = new CartItem();
			cartItem.setItem(item);
			cartItem.setCart(cart);
			cartItem.setQuantity(Integer.parseInt(quantity));

			cartDao.create(cartItem);

			cartDao.updateCart(cart, cart.getTotalQuantity() + cartItem.getQuantity(), cart.getTotalPrice() + cartItem.getQuantity() * cartItem.getItem().getPrice());

		}
		else {
			Cart cart = cartDao.getCart(u.getUserId());
			cartDao.updateCartItem(i, Integer.parseInt(quantity) + i.getQuantity());
			cartDao.updateCart(cart, cart.getTotalQuantity() + Integer.parseInt(quantity), cart.getTotalPrice() + Integer.parseInt(quantity) * i.getItem().getPrice());
		}
		request.getSession().setAttribute("result", "Added Successfully");

		return "customerSuccess";
	}

	@RequestMapping(value = "/customer-info.htm", method = RequestMethod.GET)
	public String showInfoForm(HttpServletRequest request, UserDAO userDao) {

		return "customerInfo";
	}

	@RequestMapping(value = "/customer-info.htm", method = RequestMethod.POST)
	public String handleRestaurantInfo(HttpServletRequest request, RestaurantDAO restaurantDao, UserDAO userDao, ModelMap map) {

		try {
			User u = (User) request.getSession().getAttribute("user");

			String name = request.getParameter("name");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String city = request.getParameter("city");

			u = userDao.update(u.getUserId(), address, city, phoneNumber, name);
			request.getSession().setAttribute("user", u);
			request.getSession().setAttribute("result", "Update Customer Information Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "customerSuccess";
	}

	@RequestMapping(value = "/viewCart.htm", method = RequestMethod.GET)
	public String getCart(HttpServletRequest request, CartDAO cartDao, CustomerDAO customerDao) {

		try {
			return getCartObject(request, cartDao, customerDao);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
