package com.pp.mo.filemanager.prefrences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.pp.mo.filemanager.R;

/**
 * Created by PriyabratP on 12-12-2016.
 */

public class FragmentThemePreference extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    public FragmentThemePreference() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_theme);

        /*Preference preferenceActionBar = findPreference(PreferenceManager.AppKeyNames.KEY_ACTIONBAR_COLOR);
        preferenceActionBar.setOnPreferenceChangeListener(this);*/

        Preference preferenceThemeStyle = findPreference(PreferenceManager.AppKeyNames.KEY_THEME_STYLE);
        preferenceThemeStyle.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ((PreferenceActivity)getActivity()).changeActionBarColor(Integer.valueOf(newValue.toString()));
        if(preference.getKey().contains(PreferenceManager.AppKeyNames.KEY_THEME_STYLE)){
            getActivity().recreate();
        }
        return true;
    }
}
