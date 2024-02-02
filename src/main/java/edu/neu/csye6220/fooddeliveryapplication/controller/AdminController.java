package edu.neu.csye6220.fooddeliveryapplication.controller;

import edu.neu.csye6220.fooddeliveryapplication.dao.*;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.*;
import edu.neu.csye6220.fooddeliveryapplication.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private AdminService adminService;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@GetMapping(value = "/menu.htm")
	public String getMenu(HttpServletRequest request, ItemDAO itemDAO) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			List<Item> items = itemDAO.getList(u.getUserId());
			request.getSession().setAttribute("menu", items);
			return "adminMenu";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/addItem.htm")
	public String getAddItem() {
		return "addItem";
	}

	@PostMapping(value = "/addItem.htm")
	public String postAddItem(HttpServletRequest request, RestaurantDAO restaurantDao, ItemDAO itemDAO) {
		try {
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String cuisine = request.getParameter("cuisine");
			String type = request.getParameter("type");

			User u = (User) request.getSession().getAttribute("user");
			Restaurant restaurant = restaurantDao.getRestaurant(u.getUserId());

			generateItem(itemDAO, name, price, cuisine, type, restaurant);

			return "restaurantSuccess";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void generateItem(ItemDAO itemDAO, String name, String price, String cuisine, String type, Restaurant restaurant) throws Exception {
		Item item = new Item();
		item.setName(name);
		item.setPrice(Double.parseDouble(price));
		item.setCuisine(cuisine);
		item.setType(type);
		item.setRestaurant(restaurant);
		itemDAO.create(item);
	}

	@GetMapping(value = "/updateItem.htm")
	public String showUpdateItemForm(HttpServletRequest request, ItemDAO itemDAO, ModelMap map) {
		String itemId = request.getParameter("itemId");
		request.getSession().setAttribute("itemId", itemId);
		try {
			Item item = itemDAO.getItem(Long.parseLong(itemId));
			request.getSession().setAttribute("item", item);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return "updateItem";
	}

	@PostMapping(value = "/updateItem.htm")
	public String UpdateItem(HttpServletRequest request, ItemDAO itemDAO, CartDAO cartDao) {
		try {
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String cuisine = request.getParameter("cuisine");
			String type = request.getParameter("type");

			String itemId = (String) request.getSession().getAttribute("itemId");
			if(itemId != null) {
				try {
					return updateItemObject(request, itemDAO, cartDao, name, price, cuisine, type, itemId);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String updateItemObject(HttpServletRequest request, ItemDAO itemDAO, CartDAO cartDao, String name, String price, String cuisine, String type, String itemId) throws Exception {
		Item item = itemDAO.getItem(Long.parseLong(itemId));
		double previousPrice = item.getPrice();
		boolean flag = itemDAO.updateItem(Long.parseLong(itemId), name, Double.parseDouble(price), cuisine, type);
		if(flag) {
			List<Cart> carts = cartDao.getAllCart();
			for(Cart c : carts) {
				CartItem cartItem = cartDao.getByMenuUser(item.getItemId(), c.getCartId());
				if(cartItem != null) {
					cartDao.updateCart(c, c.getTotalQuantity(), c.getTotalPrice() + (Double.parseDouble(price) - previousPrice) * cartItem.getQuantity());
				}
			}
			item = itemDAO.getItem(Long.parseLong(itemId));
			request.getSession().setAttribute("menu", item);
			return "restaurantSuccess";
		}
		else {
			return "error";
		}
	}

	@GetMapping(value = "/deleteItem.htm")
	public String deleteItem(HttpServletRequest request, RestaurantDAO restaurantDao, ItemDAO itemDAO, CartDAO cartDao, ModelMap map) {

		try {
			return deleteItemObject(request, itemDAO, cartDao);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String deleteItemObject(HttpServletRequest request, ItemDAO itemDAO, CartDAO cartDao) throws Exception {
		User u = (User) request.getSession().getAttribute("user");
		String itemId = request.getParameter("itemId");
		Item item = itemDAO.getItem(Long.parseLong(itemId));
		if(item != null) {
			List<CartItem> cartItems = cartDao.getByMenu(item.getItemId());
			for(CartItem cartItem : cartItems) {
				Cart cart = cartDao.getCart(cartItem.getCart().getCartId());
				cartDao.updateCart(cart, cart.getTotalQuantity() - cartItem.getQuantity(), cart.getTotalPrice() - cartItem.getQuantity() * cartItem.getItem().getPrice());
				cart.getItemSet().remove(cartItem);
				itemDAO.getItem(cartItem.getItem().getItemId());
				item.getCartItemSet().remove(cartItem);

				cartDao.delete(cartItem);
			}

			itemDAO.delete(item);
			return "restaurantSuccess";
		}
		else {
			return "error";
		}
	}

	@GetMapping(value = "/restaurantInfo.htm")
	public String getRestaurantInfo() {
		return "restaurantInfo";
	}

	@PostMapping(value = "/restaurantInfo.htm")
	public String postRestaurantInfo(HttpServletRequest request, UserDAO userDAO) {

		try {
			User u = (User) request.getSession().getAttribute("user");

			String name = request.getParameter("name");
			String phoneNumber = request.getParameter("phoneNumber");
			String address = request.getParameter("address");
			String city = request.getParameter("city");

			u = userDAO.update(u.getUserId(), address, city, phoneNumber, name);
			request.getSession().setAttribute("user", u);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "restaurantSuccess";
	}


	@GetMapping(value = "/receivedOrders.htm")
	public String showMyOrders(HttpServletRequest request, OrderDAO orderDAO) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			List<Order> orders = orderDAO.getOrderByRes(u.getUserId());

			request.getSession().setAttribute("orders", orders);
			return "receivedOrders";


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@GetMapping(value = "/orderDetails.htm")
	public String getOrderDetails(HttpServletRequest request, OrderDAO orderDAO) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDAO.getOrderById(Long.parseLong(orderId));
			request.getSession().setAttribute("order", order);

			List<OrderItem> orderItems = orderDAO.getOrderItemList(Long.parseLong(orderId));

			request.getSession().setAttribute("orderItems", orderItems);
			return "receivedOrderDetails";


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = "/updateOrder.htm")
	public String updateOrder(HttpServletRequest request, OrderDAO orderDao) {

		try {
			User u = (User) request.getSession().getAttribute("user");
			String orderId = request.getParameter("orderId");
			Order order = orderDao.getOrderById(Long.parseLong(orderId));
			orderDao.ResupdateOrder(order);

			return "restaurantSuccess";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
