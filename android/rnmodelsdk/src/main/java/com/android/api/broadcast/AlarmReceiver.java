package com.android.api.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.api.util.Logc;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String INTENT_ALARM_ACTION = "intent_alarm_action";
    public static OnAlarmReceiver onAlarmReceiver = null;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Logc.e("---->"+action);
        if (action == INTENT_ALARM_ACTION) {
            Logc.e("AlarmReceiver start");
            if (onAlarmReceiver != null){
                onAlarmReceiver.onReceive(context,intent);
            }
        }
    }


    public interface OnAlarmReceiver{
        void onReceive(Context context, Intent intent);
    }
}
