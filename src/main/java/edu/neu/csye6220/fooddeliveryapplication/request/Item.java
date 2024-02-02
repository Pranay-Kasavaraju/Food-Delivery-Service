package edu.neu.csye6220.fooddeliveryapplication.request;

import lombok.Data;

@Data
public class Item {
	private String name;
	private String price;
	private String cuisine;
	private String type;
}
