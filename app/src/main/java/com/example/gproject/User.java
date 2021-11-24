package com.example.gproject;

public class User {
    String username;
    String password;
    String email;
    String firstName;
    String lastName;
    int id;

    public User(String username, String password, String email, int id, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getId() {
        return id;
    }
}
