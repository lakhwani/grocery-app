package com.example.gproject;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyModel implements Contract.Model{

    public void checkValidUserCredentials(String username, String password, Contract.Presenter presenter) {

    }

    public boolean validPassword(String username) {
        return true;
    }


}