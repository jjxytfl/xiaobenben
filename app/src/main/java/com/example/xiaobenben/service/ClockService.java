package com.example.xiaobenben.service;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.TimeZone;

public class ClockService extends Service {
    private Intent intent;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private int Hour,Min;
    private Calendar calendar;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();
        /**
         * 10秒,用于测试*/
        long Minutes = 60*1000*60;
        //long triggerAtTime = SystemClock.elapsedRealtime() + Minutes;
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);

        /**
         * 理论*/
        Hour = intent.getIntExtra("Hour",0);
        Min = intent.getIntExtra("Min",0);
        calendar.setTimeZone( TimeZone.getTimeZone( "GMT+8:00" ) );
        calendar.set( Calendar.HOUR_OF_DAY, Hour );
        calendar.set( Calendar.MINUTE, Min );
        long clockTime = calendar.getTimeInMillis();
        long current = System.currentTimeMillis();
        long time = clockTime - current;
        Log.d( "TAG",clockTime+"Clock" );
        Log.d( "TAG",current+"Current" );
        Log.d( "TAG",time+"Millisecond" );



        //intent = new Intent(this, WarnActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, Minutes, pendingIntent);
        return super.onStartCommand( intent, flags, startId );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //alarmManager.cancel(pendingIntent);
    }
}
