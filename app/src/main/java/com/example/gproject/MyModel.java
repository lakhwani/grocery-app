package com.example.gproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyModel implements Contract.Model{

    FirebaseDatabase db;

    public MyModel(){
        this.db = FirebaseDatabase.getInstance("https://gruber-6b4f2-default-rtdb.firebaseio.com/");
    }

    public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter) {
        DatabaseReference ref = db.getReference("customers");
        ref.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("error", "Error getting data", task.getException());
                }
                else {
                    String un = "";
                    String pw = "";
                    Customer c = null;
                    try{
                        un = task.getResult().child("username").getValue().toString();
                        pw = task.getResult().child("password").getValue().toString();
                        c = task.getResult().getValue(Customer.class);
                    }
                    catch(Exception e){
                        Log.i("console", "null task");
                    }
                    // checks if user and password is valid
                    if(c != null && username.equals(un) && password.equals(pw)) {
                        presenter.onValidCredentials(c);
                    }
                    else{
                        presenter.onInvalidCredentials(un);
                    }
                }
            }
        });
    }
}