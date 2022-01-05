package com.android.api.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import com.android.api.broadcast.AlarmReceiver;

import java.util.Calendar;

public class TaskManager {
    private static TaskManager instance;
    private AlarmManager alarmMgr;
    private PendingIntent pendingIntent;
    public static TaskManager getInstance() {
        if (instance == null) {
            synchronized (TaskManager.class){
                if (instance == null){
                    instance = new TaskManager();
                }
            }
        }
        return instance;
    }

    public void setAlarm(Context context,int hour,int minute,AlarmReceiver.OnAlarmReceiver listener){
        if (alarmMgr == null) {
            alarmMgr = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(AlarmReceiver.INTENT_ALARM_ACTION);
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        alarmMgr.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pendingIntent);
        AlarmReceiver.onAlarmReceiver = listener;


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0低电耗模式需要使用此方法才能准时触发定时任务
//            alarmMgr.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4以上，使用此方法触发定时任务时间更为精准
//            alarmMgr.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        } else {//4.4以下，使用旧方法
//            //alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(), TIME_INTERVAL, pendingIntent);
//        }

    }

    public void setAlarm(Context context,AlarmReceiver.OnAlarmReceiver listener){
        setAlarm(context,3,0,listener);
    }

    public void cancel(){
        if (alarmMgr!= null && pendingIntent!=null) {
            alarmMgr.cancel(pendingIntent);
        }
        pendingIntent=null;
    }
}
