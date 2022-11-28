package com.example.xiaobenben.biao.ScheduleTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.xiaobenben.R;

public class scheduleTasksBiao_OperateActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tasks_biao_operate);
        context = this;

        ListView lv = findViewById(R.id.id_biao_scheduleTasks_lv);

    }
}