package com.android.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.api.comm.DeviceApi;
import com.example.yf_rk3288_api.YF_RK3288_API_Manager;

public class YF_RK3288Api extends DeviceApi {
    private YF_RK3288_API_Manager api = null;

    public YF_RK3288Api(Activity act) {
        super(act);
        api = new YF_RK3288_API_Manager(act);
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
            api.yfShutDown();
        }
    }

    @Override
    public void reboot() {
        super.reboot();
        if (api != null) {
            api.yfReboot();
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
            api.yfsetOnOffTime(timeonArray, timeoffArray, true);
        }
    }

    @Override
    public void setLCDOn() {
        super.setLCDOn();
        if (api != null) {
            api.yfSetLCDOn();
        }
    }

    @Override
    public void setLCDOff() {
        super.setLCDOff();
        if (api != null) {
            api.yfSetLCDOff();
        }
    }

    @Override
    public void setRotation(String degree) {
        super.setRotation(degree);
        if (api != null) {
            api.yfsetRotation(degree);
        }
    }

    @Override
    public void setNavigationBarVisibility(Boolean status) {
        super.setNavigationBarVisibility(status);
        if (api != null) {
            api.yfsetNavigationBarVisibility(status);
        }
    }

    @Override
    public void setStatusBarDisplay(Boolean status) {
        super.setStatusBarDisplay(status);
        if (api != null) {
            api.yfsetStatusBarDisplay(status);
        }
    }

    @Override
    public void setStatusBarVisibility(Boolean status) {
        super.setStatusBarVisibility(status);
        if (api != null) {
            api.yfsetStatusBarVisibility(status);
        }
    }
}
