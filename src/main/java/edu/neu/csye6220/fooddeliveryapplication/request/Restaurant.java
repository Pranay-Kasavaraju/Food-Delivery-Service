package edu.neu.csye6220.fooddeliveryapplication.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
List<Item> items;
String location;
}
