package com.example.gproject;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference ref;
    ArrayList<Product> list;
    ArrayAdapter<Product> adapter;
    Product product;
    String username;
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_page);

        product = new Product();
        listView = findViewById(R.id.list_items);
        ref = FirebaseDatabase.getInstance().getReference("owners");
        list = new ArrayList<Product>();
        adapter = new ArrayAdapter<Product>(this, R.layout.shop_page, R.id.list_items, list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    if (ds.getChildren("username") == username) {

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
    }
    */
}
