package com.example.gproject;

public class Customer extends User{
    Order order;

    public Customer(String username, String password, String email, int id){
        super(username, password, email, id);
    }
}
