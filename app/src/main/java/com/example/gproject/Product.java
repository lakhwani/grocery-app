package com.example.gproject;

public class Product {
    double price;
    String brand;
    int amount;

    public Product(double price, String brand, int amount){
        this.price = price;
        this.brand = brand;
        this.amount = amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }
}
