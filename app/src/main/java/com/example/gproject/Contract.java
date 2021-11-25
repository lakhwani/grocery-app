package com.example.gproject;

public interface Contract {
    public interface Model{
        public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter);
    }

    public interface View{
        public void displayMessage(String message);
    }

    public interface Presenter{
        public void checkLoginCredentials(String username, String password);
        public void onValidCredentials(Customer c);
        public void onInvalidCredentials(String m);
    }
}
