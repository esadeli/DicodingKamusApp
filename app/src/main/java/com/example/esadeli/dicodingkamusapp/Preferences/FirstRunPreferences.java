package com.example.esadeli.dicodingkamusapp.Preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.esadeli.dicodingkamusapp.R;

/**
 * Created by esadeli on 29/07/18.
 *
 * Preference for insertBulk activity
 */

public class FirstRunPreferences {

    private SharedPreferences prefs;
    private Context context;

    public FirstRunPreferences(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.app_first_run);
        editor.putBoolean(key,input);
        editor.apply();
    }

    public Boolean getFirstRun(){
        String key = context.getResources().getString(R.string.app_first_run);
        return prefs.getBoolean(key, true);
    }
}
