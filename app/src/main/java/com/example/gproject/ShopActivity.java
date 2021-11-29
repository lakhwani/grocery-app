package com.example.gproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Product> list;
//    ArrayList<String> list;
    MyAdapter adapter;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);

        Intent intent = getIntent();
        username = intent.getStringExtra(CustomerMainActivity.EXTRA_MESSAGE);
        recyclerView = findViewById(R.id.list_item);
        ref = FirebaseDatabase.getInstance().getReference("owners");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);
        recyclerView.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    if (username.equals(ds.child("username").getValue().toString())) {   // find the wanted Owner shop
                       /* for (DataSnapshot pr: ds.child("shop_products").getChildren())  {
                            double price = (double) pr.child("price").getValue();
                            String brand = (String) pr.child("brand").getValue();
                            int amount = (int) pr.child("amount").getValue();
                            list.add(new Product(price, brand, amount));
                        } */
                       // list.add(ds.child("username").getValue().toString());
                        for (DataSnapshot pr: ds.child("shop_products").getChildren()) {
                            String name = pr.child("brand").getValue().toString();
                            String price = pr.child("price").getValue().toString();
                            String amount = pr.child("amount").getValue().toString();
                            Product product = new Product(Double.parseDouble(price), name, Integer.parseInt(amount));
                            list.add(product);
                        }

                    }
                }
                adapter = new MyAdapter(getApplicationContext(), list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
