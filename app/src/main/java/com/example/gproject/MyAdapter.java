package com.example.gproject;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    Context context;
    ArrayList<Product> list;
    Order order;
    public static ArrayList<Product> order_list;


    public MyAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        order = new Order();
        order_list = new ArrayList<Product>();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_items,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product = list.get(position);
//        String str = list.get(position);
        holder.product_amount.setText(String.valueOf(product.getAmount()));
        holder.product_name.setText(product.getBrand());
        holder.product_price.setText(String.valueOf(product.getPrice()));
        holder.add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.order_amount++;
                holder.display_amount.setText(String.valueOf(product.order_amount));
                if (!order_list.contains(product)) {
                    order_list.add(product);
                } else {
                    for (Product p : order_list) {
                        if (p.brand.equals(product.brand))
                            p.order_amount = product.order_amount;
                    }
                }
            }
        });

        holder.remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.order_amount != 0) {
                    product.order_amount--;
                    holder.display_amount.setText(String.valueOf(product.order_amount));
                    if (product.order_amount!=0) {
                        for (Product p : order_list) {
                            if (p.brand.equals(product.brand))
                                p.order_amount = product.order_amount;
                        }
                    } else {
                        for (Product p: order_list) {
                            if (p.brand.equals(product.brand))
                                order_list.remove(p);
                        }
                    }

                }


            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name ,product_price, product_amount, display_amount;
        Button add_button, checkout, remove_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_amount = itemView.findViewById(R.id.product_amount);
            product_price= itemView.findViewById(R.id.product_price);
            product_name= itemView.findViewById(R.id.product_name);
            display_amount = itemView.findViewById(R.id.amount_display);
            add_button = itemView.findViewById(R.id.add_button);
            remove_button = itemView.findViewById(R.id.remove_button);
            checkout = itemView.findViewById(R.id.checkout);
        }
    }
}
