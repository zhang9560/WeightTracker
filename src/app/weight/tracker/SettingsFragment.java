package app.weight.tracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String KEY_PREF_GENDER = "pref_gender";
    public static final String KEY_PREF_HEIGHT = "pref_height";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        PreferenceManager prefManager = getPreferenceManager();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String gender = sharedPrefs.getString(KEY_PREF_GENDER, null);
        if (gender != null && !gender.isEmpty()) {
            prefManager.findPreference(KEY_PREF_GENDER).setSummary(gender);
        }

        String height = sharedPrefs.getString(KEY_PREF_HEIGHT, null);
        if (height != null && !height.isEmpty()) {
            prefManager.findPreference(KEY_PREF_HEIGHT).setSummary(height);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = getPreferenceManager().findPreference(key);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String value = sharedPrefs.getString(key, null);

        if (value != null && !value.isEmpty()) {
            preference.setSummary(value);
        } else if (key.equals(KEY_PREF_GENDER)) {
            preference.setSummary(R.string.choose_gender);
        } else if (key.equals(KEY_PREF_HEIGHT)) {
            preference.setSummary(R.string.input_height);
        }
    }
}
