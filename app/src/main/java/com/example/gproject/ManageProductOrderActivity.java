package com.example.gproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManageProductOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> list;
    ManageProductOrderAdapter adapter;
    Order order;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product_pop_up);
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("object");
        setWindowProperties();
        list = order.getCart_products();
        recyclerView = findViewById(R.id.orders_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManageProductOrderAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public void setWindowProperties(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout(width, (int)(height*0.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount = 0.5f;
        getWindow().setAttributes(params);
    }

}
