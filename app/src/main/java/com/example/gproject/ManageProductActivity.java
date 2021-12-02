package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ManageProductActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.gproject.MANAGE_PRODUCT";

    public void goBack(View v) {
        onBackPressed();
    }

    LinearLayout layout;
    ManageProductActivity mpa;
    String owner_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        layout = findViewById(R.id.manage_product_layout);
        mpa = this;
        Intent intent = getIntent();
        owner_name = intent.getStringExtra(OwnerMainActivity.EXTRA_MESSAGE);
        DB.getManageProducts(owner_name, this);
    }

    public void addProduct(View v){
        Product p = new Product(0.00,"",  0);
        Intent intent = new Intent(getApplicationContext(), ManageProductPopUp.class);
        intent.putExtra(EXTRA_MESSAGE, p);
        intent.putExtra("owner_name",owner_name);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // Collect data from the intent and use it
        super.onActivityResult(requestCode, resultCode, data);
        Product p = (Product)data.getSerializableExtra("product_that_was_added");
        addCard(p.getBrand(), p.getPrice(), p.getAmount());
//        recreate();
    }

    public void onDeletedProductRefreshView(){
        OnToast.showToast("Product deleted!", this);
        recreate();
    }

    public void addCard(String product_name, double product_price, int product_amt){
        final View view = getLayoutInflater().inflate(R.layout.manage_product_card, null);
        TextView manage_product_name = view.findViewById(R.id.manage_product_name);
        TextView manage_product_price = view.findViewById(R.id.manage_product_price);
        TextView manage_product_amount = view.findViewById(R.id.manage_product_amount);
        Button manage_product_edit = view.findViewById(R.id.manage_product_edit);

        manage_product_name.setText(Helper.trim(product_name, 20));
        @SuppressLint("DefaultLocale") String product_price_string = String.format("CA $%.2f", product_price);
        manage_product_price.setText(product_price_string);
        @SuppressLint("DefaultLocale") String product_amt_string = String.format("%d", product_amt);
        manage_product_amount.setText(product_amt_string);

        Product p = new Product(product_price,product_name,product_amt);

        manage_product_edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DB.editProductUser(owner_name, p, mpa);
            }
        });

        layout.addView(view);
    }
}