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
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.android.api.MI9Api;
import com.android.api.broadcast.AlarmReceiver;
import com.android.api.util.DeviceHelper;
import com.android.api.util.Logc;
import com.android.api.util.PermissionManager;
import com.android.api.util.ScreenBrightnessApi;
import com.android.api.util.SpManager;
import com.android.api.util.TaskManager;

public class MainActivity extends Activity {
    private SeekBar bri,aodiu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ApiUtil.init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        bri = findViewById(R.id.seekBar1);
        aodiu = findViewById(R.id.seekBar2);
        bri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        int pos = 0;//ScreenBrightnessApi.getApi().getScreenBrightnessProgress(this);
        bri.setProgress(pos);


        //aodiu.setMax(VolumeManager.getInstance().getSystemMaxVolume());
        aodiu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //aodiu.setProgress(VolumeManager.getInstance().getCurrVolume());
    }




    public void onVolUp(View view) {
    }

    public void onVolDown(View view) {
    }

    public void onDeviceInfo(View view) {
        ApiUtil.init(this);
    }


    public void onWriteFile(View view) {
        SpManager.getInstance().putString("wasai",System.currentTimeMillis()+"-1");
    }

    public void onReadFile(View view) {
        String msg = SpManager.getInstance().getString("wasai");
        Logc.e(msg);
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void onLuguage(View view) {
        MI9Api mi9Api = new MI9Api(this);
        String coutry = mi9Api.getCountry();
        Logc.i(coutry);
        Toast.makeText(this,coutry,Toast.LENGTH_SHORT).show();
    }

    public void onTask(View view) {
        TaskManager.getInstance().setAlarm(this, 11, 32, new AlarmReceiver.OnAlarmReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity.this, "定时任务开始了", Toast.LENGTH_SHORT).show();
                Logc.e("定时任务开始了");
            }
        });
        Logc.e("set定时任务开始了"+getUpTime());

        int d = DeviceHelper.getBootTime();
        Logc.e("-->"+d);
        int hour = d / 10000;
        int minute=d % 10000 /100;
        int sec=d % 100;

        Logc.e("-->hout "+hour);
        Logc.e("-->minute "+minute);
        Logc.e("-->sec "+sec);

    }
    public String getUpTime() {
        long upTime = SystemClock.elapsedRealtime()/1000;
        return convert(upTime);
    }
    private String convert(long t) {
        int s = (int)(t % 60);
        int m = (int)((t / 60) % 60);
        int h = (int)((t / 3600));

        return h + ":" + pad(m) + ":" + pad(s);
    }
    private String pad(int n) {
        if (n >= 10) {
            return String.valueOf(n);
        } else {
            return "0" + String.valueOf(n);
        }
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

}