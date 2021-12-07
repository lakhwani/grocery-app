package com.example.gproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    ArrayList<Order> orders;
    ManageOrderAdapter adapter;
    String username;

    public void goBack(View v) {
        onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
        Intent intent = getIntent();
        username = intent.getStringExtra(OwnerMainActivity.EXTRA_MESSAGE);

        recyclerView = findViewById(R.id.list_item_order);
        ref = FirebaseDatabase.getInstance("https://gruber-6b4f2-default-rtdb.firebaseio.com/").getReference().child("owners").child(username).child("customer_order");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orders = new ArrayList<>();
        adapter = new ManageOrderAdapter(ManageOrderActivity.this, orders);
        recyclerView.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    orders.add(ds.getValue(Order.class));
                }

                adapter = new ManageOrderAdapter(ManageOrderActivity.this, orders);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

   static public void removedata(Order order) {
        DatabaseReference rref = FirebaseDatabase.getInstance("https://gruber-6b4f2-default-rtdb.firebaseio.com/").getReference().child("owners").child(order.getOwner()).child("customer_order")
                .child(order.getUnique_ID());
        rref.removeValue();
    }
}
