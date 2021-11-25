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
//    static boolean b = false;
//    public static void searchUserAndEmailExists(String type_of_user, User u, Contract.View v){
//        DatabaseReference ref= db.getReference();
//        ref.orderByChild("username").equalTo(u.getUsername())
//                .addValueEventListener(new ValueEventListener(){
//                                           @Override
//                                           public void onDataChange(DataSnapshot dataSnapshot){
//                                               if(dataSnapshot.exists()){
//                                                   Log.i("console", dataSnapshot.getValue().toString());
//                                                   b=true;
//                                               }
//                                           }
//
//                                           @Override
//                                           public void onCancelled(@NonNull DatabaseError error) { }
//
//                });
//    }

    public static void searchUserAndEmailExists(String type_of_user, User u, Contract.View v){
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
                    v.displayMessage("User created");
                }
                else{
                    v.displayMessage("Username/Email is already taken!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

//    public static void searchUserAndEmailExists(String type_of_user, User u, Contract.View v){
//        db.getReference()
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        boolean b = false;
//                        // loops through child of default reference
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            Log.i("console",snapshot.getValue().toString());
//                            Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                            try{
//                                for(Object user_info: map.values()){
//                                    String user_info_string = user_info.toString();
////                                    if(user_info_string.contains()){
////                                        Log.i("console","found username");
////    //                                    v.displayMessage("This username already exists.");
////                                        b = true;
////                                    }
////                                    if(user.getEmail().equals(u.getEmail())){
////                                        Log.i("console","found email");
////    //                                    v.displayMessage("This email already exists.");
////                                        b = true;
////                                    }
//
//                                    Log.i("console", user_info_string);
//                                    Log.i("console", user_info.getClass().toString());
//                                }
//                            }
//                            catch(Exception e){
//                                Log.i("console", e.toString());
//                            }
//
//                            // then loops through child of customers/owners
////                            for(Object snap_shot_user: map.values()){
//////                                User user = snapshot.getValue(User.class);
////                                User user = (User) snap_shot_user;
////                                if(user.getUsername().equals(u.getUsername())){
////                                    Log.i("console","found username");
//////                                    v.displayMessage("This username already exists.");
////                                    b = true;
////                                }
////                                if(user.getEmail().equals(u.getEmail())){
////                                    Log.i("console","found email");
//////                                    v.displayMessage("This email already exists.");
////                                    b = true;
////                                }
////                            }
//                        }
////                        if(!b){
////                            Log.i("console","CREATED!: " + type_of_user + " " + u.getUsername());
////                            DBModel.createUser(type_of_user,u);
////                        }
//                    }
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                    }
//                });
//    }

    public static void createUser(String type_of_user, User u){
        // type of user can only be either "customers" or "owners"
        DatabaseReference ref = db.getReference();
        ref.child(type_of_user).child(u.getUsername()).setValue(u);
    }
}