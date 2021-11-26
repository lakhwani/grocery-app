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

public class CurrentUser {
    private static String username;
    private static FirebaseDatabase db;
    private static User current_user;

    public static void setUser(String un){
        username = un;
        db = DBModel.getDB();
    }

    public User getCurrentUser() {
        return current_user;
    }

    public void getUser() {
        DatabaseReference ref = db.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot user : snapshot.child("customers").getChildren()) {
                        if (user.child("username").getValue().toString().equals(username)) {
                            Log.i("console", "username found!");
                            User u = user.getValue(User.class);
                            Log.i("console", u.getEmail());
                        }
                    }
                } else {
                    Log.i("console", "snapshot doesnt exist");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

