package edu.neu.csye6220.fooddeliveryapplication.controller;

import edu.neu.csye6220.fooddeliveryapplication.dao.ItemDAO;
import edu.neu.csye6220.fooddeliveryapplication.dao.RestaurantDAO;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.Item;
import edu.neu.csye6220.fooddeliveryapplication.dao.model.Restaurant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AjaxController {
    public AjaxController(){

    }

    @RequestMapping(value = "/ajaxservice.htm", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxService(HttpServletRequest request, RestaurantDAO restaurantDao, ItemDAO itemDAO)
    {
        String queryString = request.getParameter("itemName");
        String result = "";
        try {
            List<Restaurant> restaurants = restaurantDao.getList();
            List<Item> menuList = new ArrayList<>();
            for(Restaurant restaurant : restaurants) {
                List<Item> items = itemDAO.getList(restaurant.getUserId());
                for(Item i : items) {
                    if(i.getName().toLowerCase().contains(queryString.toLowerCase())){
                        result += "<form action='addToCart.htm' method=\"GET\">\r\n" +
                                "			<div class=\"list-group\">\r\n" +
                                "				<div class=\"list-group-item\">\r\n" +
                                "					<div class=\"list-group-item-heading\">\r\n" +
                                "						<strong>" + i.getName() + "</strong> &nbsp;&nbsp;&nbsp; $" + i.getPrice() + "&nbsp;&nbsp;&nbsp;Restaurant:" + restaurant.getName() + "\r\n" +
                                "					</div>\r\n" +
                                "					<select name=\"quantity\">\r\n" +
                                "						<option value=\"1\">1</option>\r\n" +
                                "						<option value=\"2\">2</option>\r\n" +
                                "						<option value=\"3\">3</option>\r\n" +
                                "						<option value=\"4\">4</option>\r\n" +
                                "						<option value=\"5\">5</option>\r\n" +
                                "					</select> \r\n" +
                                "					<input type=\"hidden\" name=\"itemId\" value=\"" + i.getItemId() + "\">&nbsp;&nbsp;\r\n" +
                                "					<input type=\"submit\" class=\"btn btn-default\" value=\"Add To Cart\"/>\r\n" +
                                "				</div>\r\n" +
                                "			</div>\r\n" +
                                "		</form>";
                    }
                }
            }
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}