package com.pp.mo.filemanager.prefrences;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pp.mo.filemanager.BaseActivity;
import com.pp.mo.filemanager.FileManagerActivity;
import com.pp.mo.filemanager.R;

public class PreferencesActivity extends BaseActivity {

    private Toolbar toolbar;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        changeActionBarColor();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Settings");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentSettings, new FragmentPreferences())
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        themeMagic();
        changeActionBarColor();
    }
}
