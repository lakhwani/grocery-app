package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OwnerMainActivity extends AppCompatActivity {
    Owner current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);
        Intent intent = getIntent();
        current_user = (Owner)intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
        setTitle();
    }

    public void setTitle(){
        TextView title = findViewById(R.id.owner_main_title);
        String new_string = getString(R.string.owner_main_title) + " " + current_user.getFirstName() + "!";
        title.setText(new_string);
    }
}