package com.example.xiaobenben.zhong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.xiaobenben.KillProcess.KillProcess;
import com.example.xiaobenben.R;
import com.example.xiaobenben.service.ClockService;
import com.example.xiaobenben.tool.SP;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddClockActivity extends AppCompatActivity {
    private TextView mTitle, mCancel, mSave;
    private TimePicker timePicker;
    private Switch warnSwitch;
    private SP sp;
    private Context context = null;
    private TimeFormat timeFormat;
    private List<ClockTime> clockTimeList;
    private int mHour, mMin;
    private boolean isSelect;
    private KillProcess killProcess;
    private AlarmManager alarmManager;
    private Calendar calendar;
    private PendingIntent pendingIntent;
    //private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
            getWindow().setStatusBarColor( Color.TRANSPARENT );
        }
        setContentView( R.layout.activity_add_clock );
        InitView();
        InitSingle();
        timePicker.setOnTimeChangedListener( new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mHour = hourOfDay;
                mMin = minute;
            }
        } );
        warnSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSelect = isChecked;
            }
        } );
    }
    private void StartService(){
        Intent intent = new Intent( this, ClockService.class );
        intent.putExtra( "Hour",mHour );
        intent.putExtra( "Min",mMin );
        startService( intent );
    }
    private void InitView() {
        mTitle = findViewById( R.id.TitleContent );
        mCancel = findViewById( R.id.Cancel );
        mSave = findViewById( R.id.Save );
        timePicker = findViewById( R.id.TimePicker );
        warnSwitch = findViewById( R.id.WarnSwitch );
        mCancel.setText( "取消" );
        mTitle.setText( "添加闹钟" );
        mSave.setText( "存储" );
        if (context == null) {
            context = AddClockActivity.this;
        }
        clockTimeList = (List<ClockTime>) getIntent().getSerializableExtra( "list" );
        for (int i = 0; i < clockTimeList.size(); i++) {
            ClockTime clockTime = clockTimeList.get( i );
            Log.d( "TAG", clockTime.getHour() + clockTime.getMin() + "end" );
        }
    }

    private void InitSingle() {
        sp = SP.getInstance();
        timeFormat = TimeFormat.getInstance();
        killProcess = KillProcess.getInstance();
        killProcess.addActivity( AddClockActivity.this );
        calendar = Calendar.getInstance();
    }

    private void ClockWarn() {
        Intent intent = new Intent( context, WarnActivity.class );
        alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE );
        pendingIntent = PendingIntent.getActivity( context, 0, intent, 0 );
//        calendar.setTimeZone( TimeZone.getTimeZone( "GMT+8:00" ) );
//        calendar.setTimeInMillis( System.currentTimeMillis() );
//        calendar.set( Calendar.HOUR_OF_DAY, mHour );
//        calendar.set( Calendar.MINUTE, mMin );
//        alarmManager.set( AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent );
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String CurrentTime = formatter.format(curDate);
        //alarmManager.set( AlarmManager.RTC_WAKEUP, 1000*60*60, pendingIntent );
//        String hour = String.valueOf( calendar.get( Calendar.HOUR_OF_DAY ) );
//        String min = String.valueOf( calendar.get( Calendar.MINUTE ) );
//        Log.d( "TAG", calendar.getTimeInMillis() + "time" );
//        Log.d( "TAG", hour + "hour_end" );
//        Log.d( "TAG", min + "min_end" );
        Log.d( "TAG", CurrentTime + "end" );
    }
    private void InitTime() {
        sp.PutData( context, "Hour", mHour );
        sp.PutData( context, "Min", mMin );
        sp.PutData( context, "isSelect", isSelect );
    }

    public void Exit(View view) {
        sp.PutData( context,"access",false );
        ReturnActivity( ClockActivity.class );
    }

    public void Add(View view) {
        sp.PutData( context,"access",true );
        InitTime();
        StartService();
        //ClockWarn();
        ReturnActivity( ClockActivity.class );
    }

    private void ReturnActivity(Class Activity) {
        startActivity( new Intent( context, Activity ) );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        alarmManager.cancel( pendingIntent );
        //stopService( intent );
    }
}