/*
 * Copyright (C) 2019 The MoKee Open Source Project
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

package org.mokee.nfc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MoKeeNfcSetIdReceiver extends BroadcastReceiver {

    private static final String TAG = "MoKeeNfcSetIdReceiver";

    static {
        System.loadLibrary("mokee_nqnfc_id_jni");
    }

    public native int setId(byte[] uid);

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Requesting set NFC ID...");

        final byte[] uid = intent.getByteArrayExtra("uid");
        if (uid == null || uid.length != 4) {
            Log.d(TAG, "Ignored invalid uid");
            return;
        }

        final int ret = setId(uid);
        Log.d(TAG, "Set NFC ID result: " + ret);
    }

}