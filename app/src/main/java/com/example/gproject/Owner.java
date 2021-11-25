package com.example.gproject;

import java.util.ArrayList;

public class Owner extends User {
    ArrayList<Product> shop_products;
    ArrayList<Order> customer_order;
    String store_name;
    String location;

    public Owner(String username, String password, String email, int id, String firstName, String lastName){
        super(username, password, email, id, firstName, lastName);
        this.shop_products = new ArrayList<Product>();
        this.customer_order = new ArrayList<Order>();
    }

    public ArrayList<Product> getShop_products() {
        return shop_products;
    }

    public void setShop_products(ArrayList<Product> shop_products) {
        this.shop_products = shop_products;
    }

    public ArrayList<Order> getCustomer_order() {
        return customer_order;
    }

    public void setCustomer_order(ArrayList<Order> customer_order) {
        this.customer_order = customer_order;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
