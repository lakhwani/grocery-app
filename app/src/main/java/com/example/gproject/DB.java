package com.example.gproject;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DB {
    private static final FirebaseDatabase db = FirebaseDatabase.getInstance("https://gruber-6b4f2-default-rtdb.firebaseio.com/");

    public static void getStores(CustomerMainActivity cma){
        // On the customer's side in customer main activity, this function finds all the stores in the database
        DatabaseReference ref = db.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for(DataSnapshot user: snapshot.child("owners").getChildren()){
                        String store_name = user.child("store_name").getValue().toString();
                        String store_location = user.child("location").getValue().toString();
                        String store_image_link = user.child("store_image_link").getValue().toString();
                        String owner_username = user.child("username").getValue().toString();
                        cma.addCard(owner_username, store_name, store_location, store_image_link);
                    }
                }else{
                    Log.i("console", "snapshot doesnt exist");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void searchUserAndEmailExists(String type_of_user, User u, CreateAccountActivity caa){
        // this function searches and sees if the username and email exists in the database
        DatabaseReference ref= db.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean b = false;
                if (snapshot.exists()) {
                    for(DataSnapshot user: snapshot.child("customers").getChildren()){
                        if(user.child("username").getValue().toString().equals(u.getUsername())){
                            Log.i("console", "username found!");
                            b=true;
                        }
                        if(user.child("email").getValue().toString().equals(u.getEmail())){
                            Log.i("console", "email found!");
                            b=true;
                        }
                    }
                    for(DataSnapshot user: snapshot.child("owners").getChildren()){
                        if(user.child("username").getValue().toString().equals(u.getUsername())){
                            Log.i("console", "username found!");
                            b=true;
                        }
                        if(user.child("email").getValue().toString().equals(u.getEmail())){
                            Log.i("console", "email found!");
                            b=true;
                        }
                    }
                }else{
                    Log.i("console", "snapshot doesnt exist");
                }
                if(!b){
                    Log.i("console","Not found, will create user");
                    createUser(type_of_user,u);
                    caa.displayMessage("User created");
                    caa.onRegistered();
                }
                else{
                    caa.displayMessage("Username/Email is already taken!");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static void createUser(String type_of_user, User u){
        // creates a user by adding it to the database
        // type of user can only be either "customers" or "owners"
        DatabaseReference ref = db.getReference();
        if(type_of_user.equals("owners")){
            Owner owner = new Owner(u.getUsername(), u.getPassword(), u.getEmail(), 0, u.getFirstName(), u.getLastName());
            // sets default values
            owner.setLocation("");
            owner.setStore_name("");
            owner.setStore_image_link("https://techcrunch.com/wp-content/uploads/2015/03/groceries-e1554037962210.jpg");
            ref.child(type_of_user).child(owner.getUsername()).setValue(owner);
        }
        else if(type_of_user.equals("customers")){
            ref.child(type_of_user).child(u.getUsername()).setValue(u);
        }
    }

    public static void getShopProducts(String username, MyAdapter adapter, RecyclerView recyclerView, ArrayList<Product> list, ShopActivity sa){
        // retrieves shop products and fills recyclerview with products in shop activity
        DatabaseReference ref = db.getReference("owners");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()) {
                    if (username.equals(ds.child("username").getValue().toString())) {
                        for (DataSnapshot pr: ds.child("shop_products").getChildren()) {
                            String name = Helper.trim(pr.child("brand").getValue().toString(), 17);
                            String price = pr.child("price").getValue().toString();
                            String amount = pr.child("amount").getValue().toString();
                            Product product = new Product(Double.parseDouble(price), name, Integer.parseInt(amount) );
                            list.add(product);
                        }
                    }
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void addProductToUser(String user_name, Product p, ManageProductPopUp mpp){
        // After pressing add product on the pop up in manage product acitivty, this function will add the product in the database
        DatabaseReference ref = db.getReference().child("owners").child(user_name).child("shop_products");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ref.child(p.getBrand()).setValue(p);
                    mpp.onAddedProductRefreshView(p);
                }else{
                    Log.i("console", "snapshot doesnt exist");
                    ref.child(p.getBrand()).setValue(p);
                    mpp.onAddedProductRefreshView(p);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("console", "Error!");
            }
        });
    }

    public static void editProductUser(String user_name, Product p, ManageProductActivity mpa){
        // from the owner side in manage product actitivy, this deletes a product on the database
        DatabaseReference ref = db.getReference().child("owners").child(user_name).child("shop_products");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for(DataSnapshot snap_shot_product: snapshot.getChildren()) {
                        int amount = Integer.parseInt(snap_shot_product.child("amount").getValue().toString());
                        String brand = snap_shot_product.child("brand").getValue().toString();
                        double price = Double.parseDouble(snap_shot_product.child("price").getValue().toString());
                        if (amount == p.getAmount() && brand.equals(p.getBrand()) && price == p.getPrice()) {
                            ref.child(snap_shot_product.getKey()).removeValue();
                            Log.i("console", "same, therefore delete! " + snap_shot_product.getKey());
                            // refreshes the view
                            mpa.onDeletedProductRefreshView();
                        }
                    }
                }else{
                    Log.i("console", "snapshot doesnt exist");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("console", "Error!");
            }
        });
    }

    public static void addOrderToOwner(Order order, CartActivity ca){
        // On the customer side in cart activity, when the purchase button is pressed, an order is
        // sent to the owner in the database
        if (order.getCart_products() == null)   return;
        DatabaseReference ref = db.getReference().child("owners").child(order.getOwner());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String ID = Helper.createID();
                    order.setUnique_ID(ID);
                    ref.child("customer_order").child(ID).setValue(order);
                    ca.onSuccessfulPurchase();
                }else{
                    Log.i("console", "snapshot doesnt exist");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("console", "Error!");
            }
        });
    }

    public static void getManageProducts(String user_name, ManageProductActivity mpa){
        // On the owner's side of manage product activity, this function retrieves all of the owner's products
        DatabaseReference ref = db.getReference().child("owners").child(user_name).child("shop_products");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for(DataSnapshot product: snapshot.getChildren()){
                        Product p = product.getValue(Product.class);
                        mpa.addCard(p.getBrand(), p.getPrice(), p.getAmount());
                    }
                }else{
                    Log.i("console", "snapshot doesnt exist");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("console", "Error!");
            }
        });
    }

    public static void editShopDetails(String owner_username, String sn, String sl, String si, ManageStoreActivity msa) {
        //Updates the store name, store location and provided store image link
        DatabaseReference ref = db.getReference().child("owners").child(owner_username);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ref.child("store_name").setValue(sn);
                    ref.child("location").setValue(sl);
                    if(si.equals("")){
                        String simp = "https://techcrunch.com/wp-content/uploads/2015/03/groceries-e1554037962210.jpg";
                        ref.child("store_image_link").setValue(simp);
                    }
                    else{
                        ref.child("store_image_link").setValue(si);
                    }
                    OnToast.showToast("Successfully Updated!", msa);
                }else{
                    Log.i("console", "snapshot doesnt exist");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("console", "Error!");
            }
        });
    }
}