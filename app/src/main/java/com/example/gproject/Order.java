package com.example.gproject;

import java.util.ArrayList;

public class Order {
    ArrayList<Product> products;
    Owner owner;

    public Order(Owner owner){
        this.products = new ArrayList<Product>();
        this.owner = owner;
    }

    public void addProduct(Product p){
        products.add(p);
    }

    public void removeProduct(Product p){
        products.remove(p);
    }

    public void removeProduct(Product product, int amount){
    }

    public void checkOut(Customer c){
        // some database code to checkout the order
        products.clear();
    }
}
