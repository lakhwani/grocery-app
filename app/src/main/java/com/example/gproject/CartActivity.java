package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    LinearLayout layout;
    Order order;
    String store_name;

    public static final String EXTRA_MESSAGE = "com.example.gproject.CART";

    public void goBack(View v) {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        layout = findViewById(R.id.shopping_cart_layout);

        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra(EXTRA_MESSAGE);
        store_name = intent.getStringExtra("STORE_NAME");
        changeName();
        addCards();
        setPrice();
    }

    public void addCards(){
        for(Product p: order.getCart_products()){
            addCard(p.getBrand(),p.getPrice(), p.getOrderAmount());
        }
    }

    public void changeName(){
        TextView shopping_cart_location = findViewById(R.id.shopping_cart_location);
        shopping_cart_location.setText(Helper.trim(store_name, 21));
    }

    public void setPrice(){
        Button b = findViewById(R.id.shopping_cart_purchase);
        double total = 0.0d;
        for(Product p: order.getCart_products()){
            total += p.getPrice() * p.getOrderAmount();
        }
        @SuppressLint("DefaultLocale") String button_text = String.format("PURCHASE • CA $%.2f",total);
        b.setText(button_text);
    }

    public void addCard(String product_name, double product_price, int product_amt){
        final View view = getLayoutInflater().inflate(R.layout.cart_card, null);
        TextView cart_item_name = view.findViewById(R.id.cart_item_name);
        TextView cart_item_price = view.findViewById(R.id.cart_item_price);
        TextView cart_item_amount = view.findViewById(R.id.cart_item_amount);
        TextView cart_item_final_price = view.findViewById(R.id.cart_item_final_price);

        cart_item_name.setText(Helper.trim(product_name, 20));
        @SuppressLint("DefaultLocale") String product_price_string = String.format("CA $%.2f", product_price);
        cart_item_price.setText(product_price_string);
        @SuppressLint("DefaultLocale") String product_amt_string = String.format("%d", product_amt);
        cart_item_amount.setText(product_amt_string);
        @SuppressLint("DefaultLocale") String totalPrice = String.format("CA $%.2f", product_price*product_amt);
        cart_item_final_price.setText(totalPrice);

        layout.addView(view);
    }

    public void purchase(View view) {
        DB.addOrderToOwner(order, this);
    }

    public void onSuccessfulPurchase(){
        OnToast.showToast("Purchase Successful!", this);
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}