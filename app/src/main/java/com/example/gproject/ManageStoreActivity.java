package com.example.gproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ManageStoreActivity extends AppCompatActivity {

    String owner_username;

    public void goBack(View v) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_storage);
        Intent intent = getIntent();
        owner_username = intent.getStringExtra(OwnerMainActivity.EXTRA_MESSAGE);
    }

    public void displayMessage(String message){
        OnToast.showToast(message, this);
    }

    public void updateStoreDetails(View v){
        EditText msn = findViewById(R.id.manage_store_name);
        EditText msl = findViewById(R.id.manage_store_location);
        EditText msi = findViewById(R.id.manage_store_image_link);

        String sn = msn.getText().toString();
        String sl = msl.getText().toString();
        String si = msi.getText().toString();

        if(sn.equals("") || sl.equals("")){
            displayMessage("Input cannot be blank!");
        }
        else{
            DB.editShopDetails(owner_username, sn, sl, si, this);
        }
    }
}