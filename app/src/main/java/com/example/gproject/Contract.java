package com.example.gproject;

public interface Contract {
    public interface Model{
        public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter);
    }

    public interface View{
        public void displayMessage(String message);
        public void goToOwnerMain(Owner o);
        public void goToCustomerMain(Customer o);
    }

    public interface Presenter{
        public void checkLoginCredentials(String username, String password);
        public void onValidCredentials(Customer c);
        public void onValidCredentials(Owner o);
        public void onInvalidCredentials(String m);
    }
}
