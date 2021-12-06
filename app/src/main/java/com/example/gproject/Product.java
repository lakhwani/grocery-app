package com.example.gproject;

import java.io.Serializable;

public class Product implements Serializable {
    double price;
    String brand;
    int amount;
    int orderAmount = 0;

    public Product(){

    }

    public Product(double price, String brand, int amount){
        this.price = price;
        this.brand = brand;
        this.amount = amount;
    }

    public Product(double price, String brand, int amount, int orderAmount){
        this.price = price;
        this.brand = brand;
        this.amount = amount;
        this.orderAmount = orderAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public int getOrderAmount() {return orderAmount;}

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", brand='" + brand + '\'' +
                ", amount=" + amount +
                ", order_amount=" + orderAmount +
                '}';
    }
}