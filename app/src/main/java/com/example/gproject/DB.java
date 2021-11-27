package com.example.gproject;

import android.util.Log;

import com.example.gproject.activities.CreateAccountActivity;
import com.example.gproject.activities.CustomerMainActivity;
import com.example.gproject.activities.ManageProductActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DB {
    private static final FirebaseDatabase db = FirebaseDatabase.getInstance("https://gruber-6b4f2-default-rtdb.firebaseio.com/");

    public static void getStores(CustomerMainActivity cma){
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
        // type of user can only be either "customers" or "owners"
        DatabaseReference ref = db.getReference();

        if(type_of_user.equals("owners")){
            Owner owner = (Owner) u;
            // sets default values
            owner.setLocation("");
            owner.setStore_name("");
            owner.setStore_image_link("http://www.goodnet.org/photos/620x0/30501_hd.jpg");
            ref.child(type_of_user).child(owner.getUsername()).setValue(owner);
        }
        else if(type_of_user.equals("customers")){
            ref.child(type_of_user).child(u.getUsername()).setValue(u);
        }
    }

    public static void addProductToUser(String user_name, Product p){
        Owner o1 = new Owner("testOwner","testPassword","testEmail", 0,"testFirstName", "testLastName");
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(new Product(1.99,"test1",1));
        products.add(new Product(2.99,"test2",2));
        products.add(new Product(3.99,"test3",3));
        o1.setShop_products(products);
        o1.setStore_name("testStoreName");
        o1.setLocation("testStoreLocation");
        o1.setStore_image_link("http://www.goodnet.org/photos/620x0/30501_hd.jpg");
        Order order = new Order("testOwner");
        order.setCustomer("testCustomer");
        ArrayList<Order> os = new ArrayList<Order>();
        os.add(order);
        order.addProduct(new Product(1.99, "testCustomerProduct1", 1));
        order.addProduct(new Product(2.99, "testCustomerProduct2", 2));
        order.addProduct(new Product(3.99, "testCustomerProduct3", 3));
        o1.setCustomer_order(os);

        DatabaseReference ref = db.getReference();
        ref.child("owners").child("testOwner").setValue(o1);
    }

    public static void getManageProducts(String user_name, ManageProductActivity mpa){
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

}
