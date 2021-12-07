package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Contract.View {

    private Contract.Presenter presenter;
    public static final String EXTRA_MESSAGE = "com.example.gproject.CREATE_ACCOUNT";

    public void displayMessage(String message){
        OnToast.showToast(message, this);
    }

    public void handleLoginClick(View view) {
        EditText lu = findViewById(R.id.login_username);
        EditText lp = findViewById(R.id.login_password);
        String user = lu.getText().toString();
        String pass = lp.getText().toString();

        if (user.equals("") || pass.equals("")) {
            displayMessage("Input cannot be blank!");
        } else {
            presenter.checkLoginCredentials(lu.getText().toString(), lp.getText().toString());
        }
    }

    public void goToCustomerMain(Customer c){
        Intent intent = new Intent(this, CustomerMainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, c);
        startActivity(intent);
    }

    public void goToOwnerMain(User u){
        Intent intent = new Intent(this, OwnerMainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, u);
        startActivity(intent);
    }

    public void handleCreateAccountClick(View view){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "");
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MyPresenter(new DBModel(), this);
    }

}