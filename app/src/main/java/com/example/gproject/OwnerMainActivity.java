package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OwnerMainActivity extends AppCompatActivity {
    User current_user;
    public static final String EXTRA_MESSAGE = "com.example.gproject.OWNER_MAIN";

    public void toManageStore(View v){
        Intent intent = new Intent(this, ManageStoreActivity.class);
        intent.putExtra(EXTRA_MESSAGE, current_user.getUsername());
        startActivity(intent);
    }

    public void toManageProduct(View v){
        Intent intent = new Intent(this, ManageProductActivity.class);
        intent.putExtra(EXTRA_MESSAGE, current_user.getUsername());
        startActivity(intent);
    }

    public void toManageOrder(View v){
        Intent intent = new Intent(this, ManageOrderActivity.class);
        intent.putExtra(EXTRA_MESSAGE, current_user.getUsername());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);
        Intent intent = getIntent();
        current_user = (User)intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        setTitle();
    }

    public void setTitle(){
        TextView title = findViewById(R.id.owner_main_title);
        String new_string = getString(R.string.owner_main_title) + " " + Helper.trim(current_user.getFirstName(), 12) + "! 👋";
        title.setText(new_string);
    }
}