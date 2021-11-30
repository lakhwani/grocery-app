package com.example.gproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import com.example.gproject.R;
import com.example.gproject.activities.CustomerMainActivity;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Product> list;
//    ArrayList<String> list;
    MyAdapter adapter;
    String username;
    Order order;

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

                        for (DataSnapshot pr: ds.child("shop_products").getChildren()) {

                            String name = pr.child("brand").getValue().toString();
                            String price = pr.child("price").getValue().toString();
                            String amount = pr.child("amount").getValue().toString();
                            Product product = new Product(Double.parseDouble(price), name, Integer.parseInt(amount), 0);
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

    public void checkout(View view) {
        order = new Order();
        order.setOwner(username);
        order.setCart_products(MyAdapter.order_list);
        Intent intent = new Intent(getApplicationContext(), CartActivity.class);
        intent.putExtra("finalorder", order);
        startActivity(intent);
    }


}
