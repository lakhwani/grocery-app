package com.example.gproject;

public class Product {
    double price;
    String brand;
    int amount;
    int order_amount;

    public Product(double price, String brand, int amount, int order_amount){
        this.price = price;
        this.brand = brand;
        this.amount = amount;
        this.order_amount = order_amount;
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
    public int getOrderAmount() {return order_amount;}

}
