
/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.android.settings.display;

import android.content.Context;
import android.content.ContentResolver;
import android.os.Bundle;
import android.text.TextUtils;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.potato.wedges.preferences.CustomSeekBarPreference;

import libcore.util.Objects;
import java.util.ArrayList;
import java.util.List;


public class ButtonBrightnessPreferenceController extends AbstractPreferenceController implements
        PreferenceControllerMixin, Preference.OnPreferenceChangeListener {

    private static final String KEY_BUTTON_BRIGHTNESS = "button_brightness";
    private CustomSeekBarPreference mButtonBrightness;

    public ButtonBrightnessPreferenceController(Context context) {
        super(context);
    }

    @Override
    public String getPreferenceKey() {
        return KEY_BUTTON_BRIGHTNESS;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        mButtonBrightness =
                (CustomSeekBarPreference) findPreference(KEY_BUTTON_BRIGHTNESS);
        if (mButtonBrightness != null) {
            int ButtonBrightness = Settings.System.getInt(getContentResolver(),
                    Settings.System.BUTTON_BRIGHTNESS, 255);
            mButtonBrightness.setValue(ButtonBrightness / 1);
            mButtonBrightness.setOnPreferenceChangeListener(this);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mButtonBrightness) {
            int value = (Integer) newValue;
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.BUTTON_BRIGHTNESS, value * 1);
            return true;
        }
        return false;
    }
}
