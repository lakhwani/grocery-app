package com.example.gproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageProductOrderAdapter extends RecyclerView.Adapter<ManageProductOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<Product> list;


    public ManageProductOrderAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items,parent,false);
        return new ManageProductOrderAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ManageProductOrderAdapter.ViewHolder holder, int position) {

        Product product = list.get(position);
//        String str = list.get(position);
        holder.product_amount.setText(String.valueOf(product.getAmount()));
        holder.product_name.setText(product.getBrand());
        holder.product_price.setText("CA $" + String.valueOf(product.getPrice()));
   }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name ,product_price, product_amount ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_amount = itemView.findViewById(R.id.product_amount_manage);
            product_price= itemView.findViewById(R.id.product_price_manage);
            product_name= itemView.findViewById(R.id.product_name_manage);
        }
    }
}
