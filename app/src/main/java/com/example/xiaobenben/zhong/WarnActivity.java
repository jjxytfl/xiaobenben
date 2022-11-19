package com.example.xiaobenben.zhong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiaobenben.KillProcess.KillProcess;
import com.example.xiaobenben.R;
import com.example.xiaobenben.service.ClockService;

import java.util.Calendar;

public class WarnActivity extends AppCompatActivity {
    private TextView Hour,Min,Date,Week;
    private KillProcess killProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
            getWindow().setStatusBarColor( Color.TRANSPARENT );
        }
        setContentView( R.layout.activity_warn );
        InitView();
        GetSystemTime();
        Vibrator();
    }
    private void InitView(){
        Hour = findViewById( R.id.ClockHour );
        Min = findViewById( R.id.ClockMin );
        Date = findViewById( R.id.ClockDate );
        Week = findViewById( R.id.ClockWeek );
        killProcess = KillProcess.getInstance();
        killProcess.addActivity( WarnActivity.this );
    }
    private void Vibrator(){
        Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);
    }
    private void GetSystemTime(){
        Calendar calendar = Calendar.getInstance();
        int mHour = calendar.get( Calendar.HOUR_OF_DAY );
        int mMin = calendar.get( Calendar.MINUTE );
        int mMonth = calendar.get( Calendar.MONTH ) +1;
        int mDate = calendar.get( Calendar.DATE );
        int mWeek = calendar.get( Calendar.DAY_OF_WEEK );
        TimeFormat timeFormat = TimeFormat.getInstance();
        Log.d( "TAG",mHour+"hour" );
        Log.d( "TAG",mMin+"Min" );
        Log.d( "TAG",mMonth+"month" );
        Log.d( "TAG",mDate+"date" );
        Log.d( "TAG",mWeek+"week" );
        Hour.setText( timeFormat.HandleHour( mHour ) );
        Min.setText( timeFormat.HandleHour( mMin ) );
        Date.setText( mMonth+"月"+mDate+"日" );
        Week.setText( timeFormat.HandleWeek( mWeek ) );
    }
    /**
     * 再次开启闹钟*/
    public void RemindLater(View view) {
        Intent intent = new Intent( WarnActivity.this, ClockService.class );
        startService( intent );
        /**
         * 返回桌面，等待下一次闹钟提醒*/
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }

    public void StopService(View view) {
        killProcess.finishAll();
    }
}