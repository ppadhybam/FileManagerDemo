package com.pp.mo.filemanager.prefrences;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.pp.mo.filemanager.R;

import java.util.List;

/**
 * Created by PriyabratP on 12-12-2016.
 */

public class PreferenceActivity extends AppCompatPreferenceActivity {

    private Resources res;
    private int actionBarColor;
    private final Handler handler = new Handler();
    private Drawable oldBackground;
    private boolean mRecreate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        changeActionBarColor(0);
        res = getResources();
        actionBarColor = PreferenceManager.getActionBarColor(this);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeActionBarColor(0);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivityForResult(intent, PreferenceManager.AppKeyNames.FRAGMENT_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PreferenceManager.AppKeyNames.FRAGMENT_OPEN){
            if(resultCode == RESULT_FIRST_USER){
                recreate();
            }
        }
    }

    @Override
    public void recreate() {
        mRecreate = true;
        super.recreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(PreferenceManager.AppKeyNames.EXTRA_RECREATE, true);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        if(state.getBoolean(PreferenceManager.AppKeyNames.EXTRA_RECREATE)){
            setResult(RESULT_FIRST_USER);
        }
    }

    @SuppressWarnings("ResourceAsColor")
    public void changeActionBarColor(int newColor) {

        int color = newColor != 0 ? newColor : PreferenceManager.getActionBarColor(this);
        Drawable colorDrawable = new ColorDrawable(color);

        if (oldBackground == null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                colorDrawable.setCallback(drawableCallback);
            } else {
                getSupportActionBar().setBackgroundDrawable(colorDrawable);
            }

        } else {
            TransitionDrawable td = new TransitionDrawable(new Drawable[] { oldBackground, colorDrawable });
            // workaround for broken ActionBarContainer drawable handling on
            // pre-API 17 builds
            // https://github.com/android/platform_frameworks_base/commit/a7cc06d82e45918c37429a59b14545c6a57db4e4
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                td.setCallback(drawableCallback);
            } else {
                getSupportActionBar().setBackgroundDrawable(td);
            }
            td.startTransition(200);
        }
        oldBackground = colorDrawable;
        // http://stackoverflow.com/questions/11002691/actionbar-setbackgrounddrawable-nulling-background-from-thread-handler
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }


    private Drawable.Callback drawableCallback = new Drawable.Callback() {
        @Override
        public void invalidateDrawable(Drawable who) {
            getSupportActionBar().setBackgroundDrawable(who);
        }

        @Override
        public void scheduleDrawable(Drawable who, Runnable what, long when) {
            handler.postAtTime(what, when);
        }

        @Override
        public void unscheduleDrawable(Drawable who, Runnable what) {
            handler.removeCallbacks(what);
        }
    };
}
