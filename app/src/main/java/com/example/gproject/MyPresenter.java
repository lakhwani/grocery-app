package com.example.gproject;

public class MyPresenter implements Contract.Presenter{
    private Contract.Model model;
    private Contract.View view;

    public MyPresenter(Contract.Model model, Contract.View view){
        this.model = model;
        this.view = view;
    }

    public void checkLoginCredentials() {
        String username = view.getUsername();
        String password = view.getPassword();
        if(username.equals(""))
            view.displayMessage("username cannot be empty");
        else
            model.checkValidUserCredentials(username, password, this);
    }

    public void onValidCredentials(String m){
        view.displayMessage("VALID login! " + m);
    }

    public void onInvalidCredentials(String m){
        view.displayMessage("Invalid login! " + m);
    }

}