package com.example.gproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageOrderAdapter extends RecyclerView.Adapter<ManageOrderAdapter.ViewHolder> {

    Context context;
    ArrayList<Order> order_list;
    int count = 0;

    public ManageOrderAdapter(Context context, ArrayList<Order> order_list) {
        this.context = context;
        this.order_list = order_list;
    }


    @NonNull
    @Override
    public ManageOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items,parent,false);
        return new ManageOrderAdapter.ViewHolder(v);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Order order = order_list.get(position);
        holder.customer_name.setText(Helper.trim(order.customer, 12));
        holder.total_price.setText(Helper.trim("Total: " + String.format("CA $%.2f",order.final_price), 23));
        count++;
        holder.order_number.setText(String.valueOf(count));
        holder.complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageOrderActivity.removedata(order);
                ((ManageOrderActivity)context).recreate();
            }
        });
        holder.view_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to pop up page for product list
                Intent intent = new Intent(context, ManageProductOrderActivity.class);
                intent.putExtra("object", order);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return order_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView customer_name, total_price, order_number;
        Button view_order, complete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.customer_name);
            total_price = itemView.findViewById(R.id.total_price);
            view_order = itemView.findViewById(R.id.view_order);
            complete = itemView.findViewById(R.id.complete);
            order_number = itemView.findViewById(R.id.order_number);
        }
    }
}
