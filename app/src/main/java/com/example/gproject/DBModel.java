package com.example.gproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DBModel implements Contract.Model{

    private static FirebaseDatabase db;

    public DBModel(){
        this.db = FirebaseDatabase.getInstance("https://gruber-6b4f2-default-rtdb.firebaseio.com/");
    }

    public static FirebaseDatabase getDB(){
        return db;
    }

    public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter) {
        DatabaseReference ref= db.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    boolean isValid = false;
                    for(DataSnapshot user: snapshot.child("customers").getChildren()){
                        if(user.child("username").getValue().toString().equals(username) && user.child("password").getValue().toString().equals(password)){
                            Log.i("console", "login credentials are correct!");
                            isValid = true;
                            try{
                                Customer c = user.getValue(Customer.class);
                                presenter.onValidCredentials(c);
                            }
                            catch(Exception e){
                                Log.i("console", e.getMessage());
                            }
                        }
                    }
                    for(DataSnapshot user: snapshot.child("owners").getChildren()){
                        if(user.child("username").getValue().toString().equals(username) && user.child("password").getValue().toString().equals(password)){
                            Log.i("console", "login credentials are correct!");
                            isValid = true;
                            try{
                                Owner o = user.getValue(Owner.class);
                                presenter.onValidCredentials(o);
                            }
                            catch(Exception e){
                                Log.i("console", e.getMessage());
                            }
                        }
                    }
                    if(!isValid){
                        presenter.onInvalidCredentials(username);
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
}