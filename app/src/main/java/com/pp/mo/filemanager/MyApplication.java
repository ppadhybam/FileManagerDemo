package com.pp.mo.filemanager;

import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by PriyabratP on 12-12-2016.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    /*static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES);
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static synchronized MyApplication getInstance(){
        return instance;
    }
}
