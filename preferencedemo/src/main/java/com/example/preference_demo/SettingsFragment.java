package com.example.preference_demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.main_settings, rootKey);
        EditTextPreference editPreference = findPreference("key_input");
        editPreference.setSummaryProvider(new Preference.SummaryProvider<EditTextPreference>() {
            @Override
            public CharSequence provideSummary(EditTextPreference preference) {
                String text = preference.getText();
                if (TextUtils.isEmpty(text)) {
                    return "Not set";
                }
                return "Length of saved value: " + text.length();
            }
        });

        editPreference.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });

        Preference notificationsPreference = findPreference("notifications");
        notificationsPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                System.out.println(newValue);
                return true;
            }
        });

        SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                System.out.println("key:" + key + "\tvalue:" + sharedPreferences.getBoolean(key, false));
            }
        };

        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);

        PreferenceScreen preferenceScreen = getPreferenceManager().createPreferenceScreen(requireContext());
        preferenceScreen.addPreference(new EditTextPreference(requireContext()));

    }
}