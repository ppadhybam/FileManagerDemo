package com.pp.mo.filemanager.prefrences;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.pp.mo.filemanager.MyApplication;
import com.pp.mo.filemanager.R;

/**
 * Created by PriyabratP on 12-12-2016.
 */

public class PreferenceManager {

    @SuppressWarnings("WrongConstant")
    public static void changeThemeStyle(AppCompatDelegate delegate) {
        int nightMode = Integer.valueOf(getThemeStyle());
        AppCompatDelegate.setDefaultNightMode(nightMode);
        delegate.setLocalNightMode(nightMode);
    }

    @SuppressWarnings("WrongConstant")
    public static void changeThemeStyleBase(AppCompatDelegate delegate) {
        int nightMode = Integer.valueOf(getThemeStyle());
        AppCompatDelegate.setDefaultNightMode(nightMode);
        delegate.setLocalNightMode(nightMode);
    }

    public static String getThemeStyle() {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getBaseContext()).getString(AppKeyNames.KEY_THEME_STYLE, "1");
    }
    public static boolean getDisplayAdvancedDevices(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(AppKeyNames.KEY_ADVANCED_DEVICES, true);
    }

    public static boolean getDisplayFileSize(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(AppKeyNames.KEY_FILE_SIZE, true);
    }

    public static boolean getDisplayFolderSize(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(AppKeyNames.KEY_FOLDER_SIZE, false);
    }

    public static boolean getDisplayFileThumbnail(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(AppKeyNames.KEY_FILE_THUMBNAIL, true);
    }

    public static boolean getDisplayFileHidden(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(AppKeyNames.KEY_FILE_HIDDEN, false);
    }

    public static boolean getRootMode(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(AppKeyNames.KEY_ROOT_MODE, false);
    }

    public static int getActionBarColor(Context context) {
        int newColor = context.getResources().getColor(R.color.colorPrimary);
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(AppKeyNames.KEY_ACTIONBAR_COLOR, newColor);
    }

    public static int getActionBarColor() {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance().getBaseContext())
                .getInt(AppKeyNames.KEY_ACTIONBAR_COLOR, Color.BLUE);
    }

    public static boolean getFolderAnimation(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(AppKeyNames.KEY_FOLDER_ANIMATIONS, false);
    }

    public static final boolean isPinEnabled(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context).getBoolean(AppKeyNames.PIN_ENABLED, false)
                && isPinProtected(context);
    }

    public static final boolean isPinProtected(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context).getString(AppKeyNames.KEY_PIN, "") != "";
    }

    public static void setPin(Context context, String pin) {
        android.preference.PreferenceManager.getDefaultSharedPreferences(context).edit().putString(AppKeyNames.KEY_PIN, hashKeyForPIN(pin)).commit();
    }

    public static boolean checkPin(Context context, String pin) {
        pin = hashKeyForPIN(pin);
        String hashed = android.preference.PreferenceManager.getDefaultSharedPreferences(context).getString(AppKeyNames.KEY_PIN, "");
        if (TextUtils.isEmpty(pin))
            return TextUtils.isEmpty(hashed);
        return pin.equals(hashed);
    }

    private static String hashKeyForPIN(String value) {
        if (TextUtils.isEmpty(value))
            return null;
        try {
            //MessageDigest digester = MessageDigest.getInstance("MD5");
            //return Base64.encodeToString(value.getBytes(), Base64.DEFAULT);
        }
        catch (Exception e) {
        }
        return value;
    }

    public static class AppKeyNames {
        public static final String EXTRA_RECREATE = "recreate";
        public static final String KEY_ADVANCED_DEVICES = "advancedDevices";
        public static final String KEY_FILE_SIZE = "fileSize";
        public static final String KEY_FOLDER_SIZE = "folderSize";
        public static final String KEY_FILE_THUMBNAIL = "fileThumbnail";
        public static final String KEY_FILE_HIDDEN = "fileHidden";
        public static final String KEY_PIN = "pin";
        public static final String PIN_ENABLED = "pin_enable";
        public static final String KEY_ROOT_MODE = "rootMode";
        public static final String KEY_ACTIONBAR_COLOR = "actionBarColor";
        public static final String KEY_THEME_STYLE = "themeStyle";
        public static final String KEY_FOLDER_ANIMATIONS = "folderAnimations";
        public static final int FRAGMENT_OPEN = 99;
    }
}
