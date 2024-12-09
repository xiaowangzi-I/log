package com.example.first;

import android.content.Context;
import android.content.SharedPreferences;


public class Save {

    private static final String prefs_name = "name_prefs";
    private static final String name_key = "username";
    private static final String prefs_password = "word_prefs";
    private static final String word_key = "password";
    private static final String prefs_check = "check_prefs";
    private static final String check_key = "checkbox";

    private SharedPreferences nameSharedPrefs;
    private SharedPreferences passwordSharedPrefs;
    private SharedPreferences checkboxSharedPrefs;

    public Save(Context context) {
        nameSharedPrefs = context.getSharedPreferences(prefs_name, Context.MODE_PRIVATE);
        passwordSharedPrefs = context.getSharedPreferences(prefs_password, Context.MODE_PRIVATE);
        checkboxSharedPrefs = context.getSharedPreferences(prefs_check,Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        SharedPreferences.Editor editor = nameSharedPrefs.edit();
        editor.putString(name_key, username);
        editor.apply();
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = passwordSharedPrefs.edit();
        editor.putString(word_key, password);
        editor.apply();
    }

    public void setCheckbox(Boolean checkbox) {
        SharedPreferences.Editor editor = checkboxSharedPrefs.edit();
        editor.putBoolean(check_key,checkbox);
        editor.apply();
    }

    public String getUsername() {
        return nameSharedPrefs.getString(name_key, null);
    }

    public String getPassword() {
        return passwordSharedPrefs.getString(word_key, null);
    }

    public Boolean getCheckbox() {
        return checkboxSharedPrefs.getBoolean(check_key,false);
    }
}
