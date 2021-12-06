package com.example.gproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Product> list;
    //    ArrayList<String> list;
    MyAdapter adapter;
    String username;
    String store_name;
    String store_location;
    String customer_username;
    Order order;


    public void goBack(View v) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);

        Intent intent = getIntent();
        username = intent.getStringExtra(CustomerMainActivity.EXTRA_MESSAGE);
        store_name = intent.getStringExtra("STORE_NAME");
        store_location = intent.getStringExtra("STORE_LOC");
        customer_username = intent.getStringExtra(CustomerMainActivity.CUSTOMER_EXTRA_MESSAGE);
        changeName();

        recyclerView = findViewById(R.id.list_item);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);
        recyclerView.setAdapter(adapter);
        Log.i("console","oncreate");
        DB.getShopProducts(username, adapter, recyclerView, list, this);
    }

    public void changeName(){
        TextView shop_page_title = findViewById(R.id.shop_page_title);
        TextView shop_page_loc = findViewById(R.id.shop_page_location);
        shop_page_title.setText(Helper.trim(store_name, 21));
        shop_page_loc.setText(Helper.trim(store_location, 50));
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // Collect data from the intent and use it
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == -1){
            Log.i("console","success");
            onBackPressed();
        }
        else{
            Log.i("console", String.valueOf(resultCode));
        }
    }

    public void checkout(View view) {
        order = new Order();
        order.setOwner(username);
        order.setCustomer(customer_username);
        order.setCart_products(MyAdapter.order_list);
        double final_price = 0;
        for (Product p: MyAdapter.order_list)
            final_price += p.price * p.orderAmount;
        order.final_price = final_price;
        Intent intent = new Intent(getApplicationContext(), CartActivity.class);
        intent.putExtra(CartActivity.EXTRA_MESSAGE, order);
        intent.putExtra("STORE_NAME", store_name);
        OnToast.showToast("Checkout success!",this);
//        startActivity(intent);
        startActivityForResult(intent, 1);
    }


}