package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gproject.DB;
import com.example.gproject.Helper;
import com.example.gproject.R;

public class ManageProductActivity extends AppCompatActivity {

    public void goBack(View v) {
        onBackPressed();
    }

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        layout = findViewById(R.id.manage_product_layout);

        DB.getManageProducts("testOwner", this);
    }

    public void updateProductDetails(View v){
        DB.addProductToUser("testUser", null);
        addCard("Chips", 1.99, 5);
    }

    public void addCard(String product_name, double product_price, int product_amt){
        final View view = getLayoutInflater().inflate(R.layout.manage_product_card, null);
        TextView manage_product_name = view.findViewById(R.id.manage_product_name);
        TextView manage_product_price = view.findViewById(R.id.manage_product_price);
        TextView manage_product_amount = view.findViewById(R.id.manage_product_amount);

        manage_product_name.setText(Helper.trim(product_name, 20));
        @SuppressLint("DefaultLocale") String product_price_string = String.format("CA $%.2f", product_price);
        manage_product_price.setText(product_price_string);
        @SuppressLint("DefaultLocale") String product_amt_string = String.format("%d", product_amt);
        manage_product_amount.setText(product_amt_string);

        layout.addView(view);
    }
}