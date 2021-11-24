package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateAccountActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setSpinnerValues();

    }
}