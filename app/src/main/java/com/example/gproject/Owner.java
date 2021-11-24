package com.example.gproject;

public class Owner extends User {
    String store_name;
    String location;

    public Owner(String username, String password, String email, int id, String firstName, String lastName, String store_name, String location){
        super(username, password, email, id, firstName, lastName);
        this.store_name = store_name;
        this.location = location;
    }

}
