
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
        if(username.equals("") || password.equals(""))
            view.displayMessage("Input cannot be blank!");
        else
            model.checkValidUserCredentials(username, password, this);
    }

    public void onValidCredentials(Customer c){
        view.displayMessage("Valid login!");
        view.goToCustomerMain(c);
    }

    public void onValidCredentials(User u){
        view.displayMessage("Valid login!");
        view.goToOwnerMain(u);
    }

    public void onInvalidCredentials(String m){
        view.displayMessage("Invalid login!");
    }

}