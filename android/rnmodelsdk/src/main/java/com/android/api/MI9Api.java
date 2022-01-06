package com.android.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.android.api.broadcast.AlarmReceiver;
import com.android.api.comm.DeviceApi;
import com.android.api.util.DeviceHelper;
import com.android.api.util.Logc;
import com.android.api.util.PermissionManager;
import com.android.api.util.ScreenBrightnessApi;
import com.android.api.util.SpManager;
import com.android.api.util.TaskManager;
import com.android.api.util.VolumeManager;
import com.example.yf_a64_api.YF_A64_API_Manager;
import com.zcapi;

public class MI9Api extends DeviceApi {
    public MI9Api(Activity act) {
        super(act);
    }

    @Override
    public Bundle invoke(Context c, Bundle readableMap) {
        return super.invoke(c, readableMap);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
