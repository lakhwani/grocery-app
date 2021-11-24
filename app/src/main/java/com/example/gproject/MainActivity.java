package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements Contract.View{

    private Contract.Presenter presenter;

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

    public void handleClick(View view){

        presenter.checkLoginCredentials();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MyPresenter(new MyModel(), this);
    }


}