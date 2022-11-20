package com.example.xiaobenben.zhong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.xiaobenben.KillProcess.KillProcess;
import com.example.xiaobenben.R;
import com.example.xiaobenben.tool.SP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.we.swipe.helper.WeSwipe;


public class ClockActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Context context = null;
    private static List<ClockTime> clockTimeList = new ArrayList<>(  );
    private TimeFormat timeFormat;
    private ClockAdapter adapter;
    private SP sp;
    private KillProcess killProcess;
    private TextView TitleContent;
    private ClockTime clockTime;
    private Intent intent;
    private static boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE );
            getWindow().setStatusBarColor( Color.TRANSPARENT );
        }
        setContentView( R.layout.activity_clock );

        InitView();
        InitSingle();
        InitData();
        InitRecycler();
    }
    private void InitView(){
        //主
        mRecyclerView = findViewById( R.id.ClockRecycler );

        //文本 不晓得有什么用
        TitleContent = findViewById( R.id.TitleContent );
        TitleContent.setText( "" );
        if (context == null){
            context = ClockActivity.this;
        }
    }
    private void InitSingle(){
        //封装类
        timeFormat = TimeFormat.getInstance();

        //本地存储的玩意
        sp = SP.getInstance();

        //杀死所有进程的玩意
        killProcess = KillProcess.getInstance();
        killProcess.addActivity( ClockActivity.this );
    }
    private void GetCallBackData(){
        boolean access = (Boolean) sp.GetData( context,"access",false );
        if (access) {
            int Hour = (int) sp.GetData( context, "Hour", 0 );
            int Min = (int) sp.GetData( context, "Min", 0 );
            boolean isSelect = (boolean) sp.GetData( context, "isSelect", false );
            Log.d( "TAG", Hour + "firstHour" );
            Log.d( "TAG", Min + "firstMin" );
            if (Hour == 0 || Min == 0) {
                Log.d( "TAG", "null" );
            } else {
                clockTime = new ClockTime( timeFormat.HandleHour( Hour ), timeFormat.HandleHour( Min ), isSelect );
                adapter.AddItem( clockTime );
            }
        }else {
            Log.d( "TAG","Cancel Set AlarmClock" );
        }
    }
    private void InitRecycler(){
        mRecyclerView.setLayoutManager( new LinearLayoutManager( context ) );
        adapter = new ClockAdapter(  clockTimeList,context );
        mRecyclerView.setAdapter( adapter );


        //实现侧滑 的玩意 WeSwipe
        WeSwipe.attach( mRecyclerView );
    }
    /**
     * 默认数据*/
    private void InitData(){
        if (flag){
            for (int i = 6; i < 15 ; i+=2) {
                ClockTime clockTime = new ClockTime( timeFormat.HandleHour( i ),timeFormat.HandleHour( i ),false );
                clockTimeList.add( clockTime );
                clockTime.setClockTimeList( clockTimeList );
            }
        }else {
            GetCallBackData();
        }
    }

    public void Exit(View view){
        killProcess.finishAll();
    }

    public static void addClock(ClockTime clockTime){
        clockTimeList.add(clockTime);
    }

    //xml 设置的监听
    public void Add(View view){
        flag = false;
        Intent intent = new Intent( context,AddClockActivity.class );
        Bundle bundle = new Bundle(  );
        bundle.putSerializable( "list",(Serializable)clockTimeList );
        intent.putExtras( bundle );
        startActivity( intent );
    }
    private void ReturnActivity(Class Acivity){
        startActivity( new Intent( context,Acivity ) );
    }
}