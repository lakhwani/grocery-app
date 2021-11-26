package com.example.gproject;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OnToast {

    public static void showToast(String message, AppCompatActivity a){
        int duration = Toast.LENGTH_SHORT;
        Context context = a.getApplicationContext();
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
