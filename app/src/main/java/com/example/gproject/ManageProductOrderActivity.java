package com.example.gproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManageProductOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> products;
    ManageProductOrderAdapter adapter;
    Order order;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order_popup);
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("object");
        TextView title = findViewById(R.id.manage_order_popup_title);
        title.setText("Order from " + Helper.trim(order.customer,10));
        products = order.getCart_products();
        recyclerView = findViewById(R.id.orders);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManageProductOrderAdapter(this, products);
        recyclerView.setAdapter(adapter);
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void complete(View view) {
        ManageOrderActivity.removedata(order);
        Intent intent = new Intent(this, ManageOrderActivity.class);
        startActivity(intent);
    }
}