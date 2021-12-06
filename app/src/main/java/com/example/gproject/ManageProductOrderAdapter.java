package com.example.gproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageProductOrderAdapter extends RecyclerView.Adapter<ManageProductOrderAdapter.ViewHolder> {

    ArrayList<Product> list;
    Context context;

    public ManageProductOrderAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ManageProductOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_popup_items,parent,false);
        return new ManageProductOrderAdapter.ViewHolder(v);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ManageProductOrderAdapter.ViewHolder holder, int position) {
        Product product = list.get(position);
        holder.product_price.setText(Helper.trim(String.format("CA %.2f",product.getPrice()), 25));
        holder.product_name.setText(Helper.trim(product.getBrand(), 20));
        Log.i("console", product.toString());
        holder.product_amount.setText(String.valueOf(product.getOrderAmount()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, product_amount, product_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_amount = itemView.findViewById(R.id.product_amount_manage);
            product_name = itemView.findViewById(R.id.product_name_manage);
            product_price = itemView.findViewById(R.id.product_price_manage);
        }
    }
}
