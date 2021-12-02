package com.example.gproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    ArrayList<Product> cart_products;
    String customer;
    String owner;
    double final_price;

    public Order() {

    }

    public ArrayList<Product> getCart_products() {
        return cart_products;
    }

    public void setCart_products(ArrayList<Product> cart_products) {
        this.cart_products = cart_products;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Order(String owner){
        this.cart_products = new ArrayList<Product>();
        this.owner = owner;
    }

    public void addProduct(Product p){
        //Check if p is in owner.product first
        cart_products.add(p);
    }

    public void removeProduct(Product p){
        cart_products.remove(p);
    }

    public void removeProduct(Product product, int amount){
    }

    public void checkOut(Customer c){
        // some database code to checkout the order
        cart_products.clear();
    }
}
