package com.example.demo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SPHelper {

    private static SPHelper instance;
    private Context context;

    private SPHelper(Context context) {
        this.context = context;
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public synchronized static SPHelper getInstance(Context context) {
        if (instance == null)
            instance = new SPHelper(context);
        return instance;
    }

    private SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    public boolean saveUserId(int uid) {
        android.content.SharedPreferences.Editor editor = getEditor();
        editor.putInt("uid", uid);
        return editor.commit();
    }

    public int getUserId() {
        return getSharedPreferences().getInt("uid", 0);
    }

    public boolean saveUserEmail(String email) {
        android.content.SharedPreferences.Editor editor = getEditor();
        editor.putString("email", email);
        return editor.commit();
    }

    public String getUserEmail() {
        return getSharedPreferences().getString("email", "");
    }


    public boolean setRemeber(int remeber) {
        android.content.SharedPreferences.Editor editor = getEditor();
        editor.putInt("remeber", remeber);
        return editor.commit();
    }

    public int getRemeber() {
        return getSharedPreferences().getInt("remeber", 0);
    }

}
