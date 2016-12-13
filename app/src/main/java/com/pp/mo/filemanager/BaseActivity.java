package com.pp.mo.filemanager;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;

import com.pp.mo.filemanager.prefrences.PreferenceManager;
import com.pp.mo.filemanager.utils.SystemBarTintManager;
import com.pp.mo.filemanager.utils.Utils;

/**
 * Created by PriyabratP on 13-12-2016.
 */

public class BaseActivity extends AppCompatActivity {

    private int themeId = 0;
    private Drawable oldBackground;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themeMagic();
        super.onCreate(savedInstanceState);
    }

    public void themeMagic() {
        PreferenceManager.changeThemeStyleBase(getDelegate());
        if(themeId!=0){
            if(Build.VERSION.SDK_INT >= 23)
            {
                onApplyThemeResource(getTheme(),themeId,false);
            }
            else
            {
                setTheme(themeId);
            }
        }
    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(resid);
        this.themeId = resid;
    }

    public void recreate() {
        PreferenceManager.changeThemeStyle(getDelegate());
        super.recreate();
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
        setUpStatusBar(color);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void changeActionBarColor() {

        int color = PreferenceManager.getActionBarColor(this);
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
        setUpStatusBar();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setUpStatusBar(int col) {
        int color = Utils.getStatusBarColor(col);
        if(Utils.hasLollipop()){
            getWindow().setStatusBarColor(color);
        }
        else if(Utils.hasKitKat()){
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setTintColor(color);
            systemBarTintManager.setStatusBarTintEnabled(true);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setUpStatusBar() {
        int color = Utils.getStatusBarColor(PreferenceManager.getActionBarColor(this));
        if(Utils.hasLollipop()){
            getWindow().setStatusBarColor(color);
        }
        else if(Utils.hasKitKat()){
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setTintColor(color);
            systemBarTintManager.setStatusBarTintEnabled(true);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
