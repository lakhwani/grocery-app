package com.example.gproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gproject.R;
import com.example.gproject.activities.CustomerMainActivity;

public class ShopActivity extends AppCompatActivity {

    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);

        Intent intent = getIntent();
        user_name = intent.getStringExtra(CustomerMainActivity.EXTRA_MESSAGE);
        Log.i("console",user_name);

//        product = new Product();
//        listView = findViewById(R.id.list_items);
//        ref = FirebaseDatabase.getInstance().getReference("owners");
//        list = new ArrayList<Product>();
//        adapter = new ArrayAdapter<Product>(this, R.layout.shop_page, R.id.list_items, list);
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds: snapshot.getChildren()) {
//                    if (ds.getChildren("username") == username) {
//
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        })
    }

}
