package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class ManageStorageActivity extends AppCompatActivity {

    public void goBack(View v) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_storage);
    }

    public void updateStoreDetails(View v){
        EditText msn = findViewById(R.id.manage_store_name);
        EditText msl = findViewById(R.id.manage_store_location);
        EditText sil = findViewById(R.id.manage_store_image_link);

        String sn = msn.getText().toString();
        String sl = msl.getText().toString();
        String il = sil.getText().toString();

//        // add regex stuff, if any of these regexes are not satisfied, user is not created
//        if(af.equals("") || al.equals("") || ae.equals("") || au.equals("")){
//            // display message that says "smth cannot be blank"
//            displayMessage("Input cannot be blank!");
//        }
//        else{
//            String type = cs.getSelectedItem().toString().toLowerCase() + "s";
//            User u = new User(au, ap, ae, 0, af, al);
//            DB.searchUserAndEmailExists(type, u, this); // searches if user and email exists, then if not, creates the user
//        }
    }
}