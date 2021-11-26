package com.example.gproject;

public class Helper {

    // class for helper functions
    public static String trim(String s, int amt){
        String trimmed = "";
        if(s.length() >= amt && amt >= 3){
            trimmed += s.substring(0,amt - 3) + "...";
            return trimmed;
        }
        return s;
    }

}
