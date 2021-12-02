package com.example.gproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ManageProductPopUp extends AppCompatActivity {

    public void goBack(View v) {
        OnToast.showToast("No product added", this);
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    Product product;
    String owner_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product_pop_up);

        Intent intent = getIntent();
        product = (Product)intent.getSerializableExtra(ManageProductActivity.EXTRA_MESSAGE);
        owner_name = intent.getStringExtra("owner_name");
        setWindowProperties();
        setWindowText();

        Button addButton = findViewById(R.id.manage_product_pop_up_update);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText mb = findViewById(R.id.manage_product_pop_up_brand);
                EditText mp = findViewById(R.id.manage_product_pop_up_price);
                EditText ma = findViewById(R.id.manage_product_pop_up_amt);
                Product new_product = new Product(Double.parseDouble(mp.getText().toString()), mb.getText().toString(), Integer.parseInt(ma.getText().toString()));

                DB.addProductToUser(owner_name, new_product, ManageProductPopUp.this);
            }
        });
    }

    public void onAddedProductRefreshView(Product new_product){
        OnToast.showToast("New product created!", this);
        Intent intent = new Intent();
        intent.putExtra("product_that_was_added", new_product);
        setResult(RESULT_OK, intent);
        finish();
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

    @SuppressLint("DefaultLocale")
    public void setWindowText(){
        EditText mb = findViewById(R.id.manage_product_pop_up_brand);
        EditText mp = findViewById(R.id.manage_product_pop_up_price);
        EditText ma = findViewById(R.id.manage_product_pop_up_amt);

        mb.setText(Helper.trim(product.getBrand(),19));
        mp.setText(String.format("%.2f",product.getPrice()));
        ma.setText(String.format("%d",product.getAmount()));
    }
}