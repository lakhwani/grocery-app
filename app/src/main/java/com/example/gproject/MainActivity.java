package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Contract.View{

    private Contract.Presenter presenter;
    public static final String EXTRA_MESSAGE = "com.example.gproject.CREATE_ACCOUNT";

    public void displayMessage(String message){
        TextView textView = findViewById(R.id.debugView);
        textView.setText(message);
    }

    public void handleLoginClick(View view){
        EditText lu = findViewById(R.id.login_username);
        EditText lp = findViewById(R.id.login_password);
        presenter.checkLoginCredentials(lu.getText().toString(), lp.getText().toString());
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