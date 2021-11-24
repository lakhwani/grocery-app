package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Contract.View{

    private Contract.Presenter presenter;
    public static final String EXTRA_MESSAGE = "com.example.gproject.CREATE_ACCOUNT";

    public void displayMessage(String message){
        TextView textView = findViewById(R.id.debugView);
        textView.setText(message);
    }

    public String getUsername(){
        EditText editText = findViewById(R.id.login_username);
        return editText.getText().toString();
    }

    public String getPassword() {
        EditText editText = findViewById(R.id.login_password);
        return editText.getText().toString();
    }

    public void handleLoginClick(View view){
        presenter.checkLoginCredentials();
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

        presenter = new MyPresenter(new MyModel(), this);
    }


}