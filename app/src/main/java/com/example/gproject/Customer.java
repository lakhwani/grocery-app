package com.example.gproject;

import java.io.Serializable;

public class Customer extends User implements Serializable {
    private Order order;

    private Customer(){
        super();
    };

    public Customer(String username, String password, String email, int id, String firstName, String lastName){
        super(username, password, email, id, firstName, lastName);
    }

    public void createOrder(String owner){
        this.order = new Order(owner);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    //create submit order function
}
