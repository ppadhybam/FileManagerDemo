package com.pp.mo.filemanager.prefrences;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PriyabratP on 12-12-2016.
 */

public class AppCompatPreferenceActivity extends PreferenceActivity {

    private AppCompatDelegate appCompatDelegate;
    private int themeId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceManager.changeThemeStyle(getAppCompatDelegate());
        appCompatDelegate.installViewFactory();
        appCompatDelegate.onCreate(savedInstanceState);
        if(appCompatDelegate.applyDayNight() && themeId!=0){
            if(Build.VERSION.SDK_INT >= 23)
            {
                onApplyThemeResource(getTheme(),themeId,false);
            }
            else
            {
                setTheme(themeId);
            }
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(resid);
        this.themeId = resid;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getAppCompatDelegate().onPostCreate(savedInstanceState);
    }

    public AppCompatDelegate getAppCompatDelegate(){
        if(appCompatDelegate==null){
            appCompatDelegate = AppCompatDelegate.create(this,null);
        }
        return appCompatDelegate;
    }
    public ActionBar getSupportActionBar() {
        return getAppCompatDelegate().getSupportActionBar();
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getAppCompatDelegate().setSupportActionBar(toolbar);
    }

    @Override
    public MenuInflater getMenuInflater() {
        return getAppCompatDelegate().getMenuInflater();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getAppCompatDelegate().setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        getAppCompatDelegate().setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getAppCompatDelegate().setContentView(view, params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getAppCompatDelegate().addContentView(view, params);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getAppCompatDelegate().onPostResume();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getAppCompatDelegate().setTitle(title);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getAppCompatDelegate().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getAppCompatDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getAppCompatDelegate().onDestroy();
    }

    public void invalidateOptionsMenu() {
        getAppCompatDelegate().invalidateOptionsMenu();
    }
    @Override
    public void recreate() {
        PreferenceManager.changeThemeStyle(getAppCompatDelegate());
        super.recreate();
    }
}
