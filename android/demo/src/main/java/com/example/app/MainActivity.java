package com.example.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.api.MI9Api;
import com.android.api.broadcast.AlarmReceiver;
import com.android.api.comm.DeviceApi;
import com.android.api.util.DeviceHelper;
import com.android.api.util.Logc;
import com.android.api.util.PermissionManager;
import com.android.api.util.ScreenBrightnessApi;
import com.android.api.util.SpManager;
import com.android.api.util.TaskManager;

public class MainActivity extends Activity implements View.OnClickListener {
    private SeekBar sb_volume,sb_brightness;
    private TextView text_log;
    private Button btn_all_info;
    private Button btn_retation_1,btn_retation_2,btn_retation_3,btn_retation_4;
    private Button btn_screen_on,btn_screen_off,btn_shutdown,btn_reboot,btn_wifi,btn_lunguage,btn_test;
    private DeviceApi deviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int pid = android.os.Process.myPid();
        Logc.e("1++++++++++++++"+pid);
        deviceApi = ApiUtil.getApi(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Bundle bundle = new Bundle();
        deviceApi.getAllInfo(bundle);
        String str = bundle.toString();
        log(str);
        int volume = bundle.getInt("volume");
        int brightness = bundle.getInt("brightness");
        sb_brightness.setProgress(brightness);
        sb_volume.setProgress(volume);
    }

    private void log(String str){
        if (!TextUtils.isEmpty(str) && text_log != null) {
            text_log.setText(str);
        }
    }

    private void initView(){
        text_log = findViewById(R.id.text_log);
        btn_all_info = findViewById(R.id.btn_all_info);
        btn_retation_1 = findViewById(R.id.btn_retation_1);
        btn_retation_2 = findViewById(R.id.btn_retation_2);
        btn_retation_3 = findViewById(R.id.btn_retation_3);
        btn_retation_4 = findViewById(R.id.btn_retation_4);
        btn_screen_on = findViewById(R.id.btn_screen_on);
        btn_screen_off = findViewById(R.id.btn_screen_off);
        btn_shutdown = findViewById(R.id.btn_shutdown);
        btn_reboot = findViewById(R.id.btn_reboot);
        btn_wifi = findViewById(R.id.btn_wifi);
        btn_lunguage = findViewById(R.id.btn_lunguage);
        btn_test = findViewById(R.id.btn_test);
        btn_test.setOnClickListener(this);
        btn_all_info.setOnClickListener(this);
        btn_retation_1.setOnClickListener(this);
        btn_retation_2.setOnClickListener(this);
        btn_retation_3.setOnClickListener(this);
        btn_retation_4.setOnClickListener(this);
        btn_screen_on.setOnClickListener(this);
        btn_screen_off.setOnClickListener(this);
        btn_shutdown.setOnClickListener(this);
        btn_reboot.setOnClickListener(this);
        btn_wifi.setOnClickListener(this);
        btn_lunguage.setOnClickListener(this);
        sb_brightness = findViewById(R.id.sb_brightness);
        sb_volume = findViewById(R.id.sb_volume);
        sb_brightness.setMax(100);
        sb_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                deviceApi.setScreenBrightness(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        sb_volume.setMax(100);
        sb_volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                deviceApi.setVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ApiUtil.onRequestPermissionsResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ApiUtil.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_all_info:
                Bundle bundle = new Bundle();
                deviceApi.getAllInfo(bundle);
                String str = bundle.toString();
                log(str);
                int volume = bundle.getInt("volume");
                int brightness = bundle.getInt("brightness");
                sb_brightness.setProgress(brightness);
                sb_volume.setProgress(volume);
                break;
            case R.id.btn_retation_1:
                deviceApi.setRotation("0");
                break;
            case R.id.btn_retation_2:
                deviceApi.setRotation("90");
                break;
            case R.id.btn_retation_3:
                deviceApi.setRotation("180");
                break;
            case R.id.btn_retation_4:
                deviceApi.setRotation("270");
                break;
            case R.id.btn_screen_on:
                deviceApi.setLCDOn();
                break;
            case R.id.btn_screen_off:
                deviceApi.setLCDOff();
                break;
            case R.id.btn_shutdown:
                deviceApi.shutdown();
                break;
            case R.id.btn_reboot:
                Logc.e("4.....onReceive");
                deviceApi.reboot();
                break;
            case R.id.btn_lunguage:
                deviceApi.gotoWigotoLanguageFi(this);
                break;
            case R.id.btn_wifi:
                deviceApi.gotoWiFi(this);
                break;
            case R.id.btn_test:

                ScreenBrightnessApi.getApi().allowModifySettings(MainActivity.this, new ScreenBrightnessApi.OnPermissionListener() {
                    @Override
                    public void onPermission(boolean granted) {
                        Logc.i("1.allowModifySettings.权限成功============" + granted);
                        PermissionManager.getInstance().requestPermissions(MainActivity.this, new PermissionManager.OnPermissionListener() {
                            @Override
                            public void onPermission(boolean granted) {
                                if(granted){
                                    Logc.i("1.requestPermissions.权限成功============" + granted);
                                    SpManager.getInstance().init(MainActivity.this,"conf");
                                }else{
                                    Logc.i("1.requestPermissions.权限失败=========" + granted);
                                }
                            }
                        });
                    }
                });
                break;
        }
    }
}