package com.example.gproject;

public interface Contract {
    public interface Model{
        public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter);
    }

    public interface View{
        public void displayMessage(String message);
        public void goToOwnerMain(User u);
        public void goToCustomerMain(Customer o);
    }

    public interface Presenter{
        public void checkLoginCredentials(String username, String password);
        public void onValidCredentials(Customer c);
        public void onValidCredentials(User u);
        public void onInvalidCredentials(String m);
    }
}