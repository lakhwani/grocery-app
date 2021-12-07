package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gproject.DB;
import com.example.gproject.OnToast;
import com.example.gproject.R;
import com.example.gproject.User;

public class CreateAccountActivity extends AppCompatActivity{
    public void goBack(View v) {
        onBackPressed();
    }

    void setSpinnerValues(){
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.choice_spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"Customer", "Owner"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

    public void displayMessage(String message){
        OnToast.showToast(message, this);
    }

    public void wrongInputShake(EditText et){
        Animation example= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        et.startAnimation(example);
    }

    public void onRegistered(){
        onBackPressed();
    }

    public void onRegister(View v){
        EditText caf = findViewById(R.id.create_account_firstname);
        EditText cal = findViewById(R.id.create_account_lastname);
        EditText cae = findViewById(R.id.create_account_email);
        EditText cau = findViewById(R.id.create_account_username);
        EditText cap = findViewById(R.id.create_account_password);
        Spinner cs = findViewById(R.id.choice_spinner);

        String af = caf.getText().toString();
        String al = cal.getText().toString();
        String ae = cae.getText().toString();
        String au = cau.getText().toString();
        String ap = cap.getText().toString();

        // add regex stuff, if any of these regexes are not satisfied, user is not created
        if(af.equals("") || al.equals("") || ae.equals("") || au.equals("") || ap.equals("")){
            // display message that says "smth cannot be blank"
            displayMessage("Input cannot be blank!");
        }
        else{
            String type = cs.getSelectedItem().toString().toLowerCase() + "s";
            User u = new User(au, ap, ae, 0, af, al);
            DB.searchUserAndEmailExists(type, u, this); // searches if user and email exists, then if not, creates the user
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setSpinnerValues();
    }
}