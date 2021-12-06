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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product_pop_up);
        Intent intent = getIntent();
        list = (ArrayList<Product>) intent.getSerializableExtra("object");
//        Log.i("d","here?");
        recyclerView = findViewById(R.id.orders_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ManageProductOrderAdapter(getApplicationContext(), list);
        recyclerView.setAdapter(adapter);
    }


}
