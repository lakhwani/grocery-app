package com.example.gproject;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        ref.child(type_of_user).child(u.getUsername()).setValue(u);
    }

}
