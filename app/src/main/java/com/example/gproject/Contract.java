package com.example.gproject;

public interface Contract {
    public interface Model{
        public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter);
        public boolean validPassword(String username);
    }

    public interface View{
        public String getUsername();
        public String getPassword();
        public void displayMessage(String message);
    }

    public interface Presenter{
        public void checkLoginCredentials();
        public void onValidCredentials(String m);
        public void onInvalidCredentials(String m);
    }
}
