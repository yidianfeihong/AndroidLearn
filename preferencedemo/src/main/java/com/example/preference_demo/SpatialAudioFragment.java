package com.example.preference_demo;

import android.os.Bundle;
import com.coui.appcompat.preference.COUIPreferenceFragment;


public class SpatialAudioFragment extends COUIPreferenceFragment {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        super.onCreatePreferences(savedInstanceState, rootKey);
        addPreferencesFromResource(R.xml.spatial_audio_preference);
    }

}