package com.example.gproject;

import java.util.ArrayList;

public class Owner extends User {
    ArrayList<Product> shop_products;
    ArrayList<Order> customer_order;
    String store_name;
    String location;

    public Owner(String username, String password, String email, int id, String firstName, String lastName, String store_name, String location){
        super(username, password, email, id, firstName, lastName);
        this.store_name = store_name;
        this.location = location;
    }

}
