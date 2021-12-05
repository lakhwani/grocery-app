
package com.example.gproject;

import android.content.Intent;

public class MyPresenter implements Contract.Presenter{
    private Contract.Model model;
    private Contract.View view;

    public MyPresenter(Contract.Model model, Contract.View view){
        this.model = model;
        this.view = view;
    }

    public void checkLoginCredentials(String username, String password) {
        if(username.equals(""))
            view.displayMessage("username cannot be empty");
        else
            model.checkValidUserCredentials(username, password, this);
    }

    public void onValidCredentials(Customer c){
        view.displayMessage("VALID login! " + c.getEmail());
        view.goToCustomerMain(c);
    }

    public void onValidCredentials(User u){
        view.displayMessage("VALID login! " + u.getEmail());
        view.goToOwnerMain(u);
    }

    public void onInvalidCredentials(String m){
        view.displayMessage("Invalid login! " + m);
    }

}