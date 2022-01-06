package com.android.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.api.comm.DeviceApi;
import com.zcapi;

public class ZCKJApi extends DeviceApi {
    private zcapi api = null;

    public ZCKJApi(Activity act) {
        super(act);
        api = new zcapi();
        api.getContext(act);
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

    @Override
    public void shutdown() {
        super.shutdown();
        if (api != null) {
            api.shutDown();
        }
    }

    @Override
    public void reboot() {
        super.reboot();
        if (api != null) {
            api.reboot();
        }
    }

    @Override
    public void setOnOffTime(final Bundle readableMap) {
        super.setOnOffTime(readableMap);
        if (api != null) {
            int[] timeonArray = {
                    readableMap.getInt("on_year"),
                    readableMap.getInt("on_month"),
                    readableMap.getInt("on_day"),
                    readableMap.getInt("on_hour"),
                    readableMap.getInt("on_minute")};
            int[] timeoffArray = {
                    readableMap.getInt("off_year"),
                    readableMap.getInt("off_month"),
                    readableMap.getInt("off_day"),
                    readableMap.getInt("off_hour"),
                    readableMap.getInt("off_minute")};
            api.setPowetOnOffTime(true,timeonArray, timeoffArray);
        }
    }

    @Override
    public void setLCDOn() {
        super.setLCDOn();
        if (api != null) {
            api.setLcdOnOff(true);
        }
    }

    @Override
    public void setLCDOff() {
        super.setLCDOff();
        if (api != null) {
            api.setLcdOnOff(false);
        }
    }

    @Override
    public void setRotation(String degree) {
        super.setRotation(degree);
        if (api != null) {
        }
    }

    @Override
    public void setNavigationBarVisibility(Boolean status) {
        super.setNavigationBarVisibility(status);
        if (api != null) {
            api.setGestureStatusBar(status);
        }
    }

    @Override
    public void setStatusBarDisplay(Boolean status) {
        super.setStatusBarDisplay(status);
        if (api != null) {
            api.setStatusBar(status);
        }
    }

    @Override
    public void setStatusBarVisibility(Boolean status) {
        super.setStatusBarVisibility(status);
        if (api != null) {
            api.setStatusBar(status);
        }
    }
}
