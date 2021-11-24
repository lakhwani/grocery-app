package com.example.gproject;

public class Customer extends User{
    private Order order;//


    public Customer(String username, String password, String email, int id, String firstName, String lastName){
        super(username, password, email, id, firstName, lastName);
    }

    public void createOrder(Owner owner){
        this.order = new Order(owner);
    }

    //create submit order function
}
