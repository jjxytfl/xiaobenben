package com.example.xiaobenben.biao.ScheduleTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.xiaobenben.R;

public class scheduleTasksBiao_HistoryActivity extends AppCompatActivity {
    private ScheduleTasksBiao scheduleTasksBiao;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tasks_biao_history);

        context = this;
        scheduleTasksBiao = new ScheduleTasksBiao();

        int biaoid;
        {
            biaoid = getIntent().getIntExtra("biaoid",0);
            scheduleTasksBiao = (ScheduleTasksBiao) getIntent().getSerializableExtra("biao");
        }


        ListView lv = findViewById(R.id.id_biao_scheduleTasks_history_tv);
        lv.setAdapter(new scheduleTasks_HistoryAdapter(scheduleTasksBiao,context));


    }
}