package com.pp.mo.filemanager.prefrences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.pp.mo.filemanager.R;

/**
 * Created by PriyabratP on 12-12-2016.
 */

public class FragmentPreferences extends PreferenceFragment implements Preference.OnPreferenceChangeListener {


    private Preference pin_set_preference;

    public FragmentPreferences() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_all);

        // Theme Settings
        Preference preferenceActionBar = findPreference(PreferenceManager.AppKeyNames.KEY_ACTIONBAR_COLOR);
        preferenceActionBar.setOnPreferenceChangeListener(this);

        Preference preferenceThemeStyle = findPreference(PreferenceManager.AppKeyNames.KEY_THEME_STYLE);
        preferenceThemeStyle.setOnPreferenceChangeListener(this);

        //Security Settings
        pin_set_preference = findPreference("pin_set");
        pin_set_preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            @Override
            public boolean onPreferenceClick(Preference preference) {
                //checkPin();
                return false;
            }
        });
        pin_set_preference.setSummary("Not Set");


    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ((PreferencesActivity)getActivity()).changeActionBarColor(Integer.valueOf(newValue.toString()));
        //((PreferencesActivity)getActivity()).setUpStatusBar();
        if(preference.getKey().contains(PreferenceManager.AppKeyNames.KEY_THEME_STYLE)){
            getActivity().recreate();
            //((PreferencesActivity)getActivity()).themeMagic();
        }
        return true;
    }
}
