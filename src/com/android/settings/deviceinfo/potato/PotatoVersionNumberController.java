/*
 * Copyright (C) 2018 The Potato Open Sauce Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.potato;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.View;

import com.android.settings.R;
import com.android.settings.Utils;

public class PotatoVersionNumberController {

    private static final String PROP = "persist.potato.version";
    private static final int POTATO_VERSION_LABEL_ID = R.id.potato_version_number_label;
    private static final int POTATO_VERSION_VALUE_ID = R.id.potato_version_number_value;

    private final PotatoVersionDialogFragment mDialog;

    PotatoVersionNumberController(PotatoVersionDialogFragment dialog) {
        mDialog = dialog;
    }

    public void initialize() {
        if(TextUtils.isEmpty(SystemProperties.get(PROP, ""))) {
            mDialog.removeSettingFromScreen(POTATO_VERSION_LABEL_ID);
            mDialog.removeSettingFromScreen(POTATO_VERSION_VALUE_ID);
        } else {
            mDialog.setText(POTATO_VERSION_VALUE_ID, SystemProperties.get(PROP, ""));
        }
    }
}
