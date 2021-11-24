package com.example.gproject;

public class MyModel implements Contract.Model{

    public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter) {
    }

    public boolean validPassword(String username) {
        return true;
    }


}