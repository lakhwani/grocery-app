package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        layout = findViewById(R.id.shopping_cart_layout);

//        order = new Order("Jonathan");
//        ArrayList<Product> cart = new ArrayList<Product>();
//        cart.add(new Product(1.99,"Apple",3));
//        cart.add(new Product(13.99,"Chicken Nuggies",7));
//        cart.add(new Product(3.49,"Soy sauce",1));
//        cart.add(new Product(5.69,"Milk",1));
//        cart.add(new Product(1.28,"Salted Chips",2));
//        cart.add(new Product(129.99,"Wagyu A5 Steak",1));
//        cart.add(new Product(2.99,"Cookies",13));
//        cart.add(new Product(4.99,"Bear Paws",3));
//        order.setCart_products(cart);

        addCards();
        setPrice();
    }

    public void addCards(){
        for(Product p: order.getCart_products()){
            addCard(p.getBrand(),p.price, p.amount);
        }
    }

    public void setPrice(){
        Button b = findViewById(R.id.shopping_cart_purchase);
        double total = 0.0d;
        for(Product p: order.getCart_products()){
            total += p.price * p.amount;
        }
        @SuppressLint("DefaultLocale") String button_text = String.format("PURCHASE * CA $%.2f",total);
        b.setText(button_text);
    }

    public void addCard(String product_name, double product_price, int product_amt){
        final View view = getLayoutInflater().inflate(R.layout.cart_card, null);
        TextView cart_item_name = view.findViewById(R.id.cart_item_name);
        TextView cart_item_price = view.findViewById(R.id.cart_item_price);
        TextView cart_item_amount = view.findViewById(R.id.cart_item_amount);
        TextView cart_item_final_price = view.findViewById(R.id.cart_item_final_price);

        cart_item_name.setText(Helper.trim(product_name, 25));
        @SuppressLint("DefaultLocale") String product_price_string = String.format("CA $%.2f", product_price);
        cart_item_price.setText(product_price_string);
        @SuppressLint("DefaultLocale") String product_amt_string = String.format("%d", product_amt);
        cart_item_amount.setText(product_amt_string);
        @SuppressLint("DefaultLocale") String totalPrice = String.format("CA $%.2f", product_price*product_amt);
        cart_item_final_price.setText(totalPrice);

        layout.addView(view);
    }
}