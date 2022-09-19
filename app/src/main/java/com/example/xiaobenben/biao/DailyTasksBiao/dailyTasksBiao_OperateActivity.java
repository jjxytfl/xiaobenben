package com.example.xiaobenben.biao.DailyTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.ben.Ben;

public class dailyTasksBiao_OperateActivity extends AppCompatActivity {

    public static DailyTasksBiao dailyTasksBiao = new DailyTasksBiao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks_biao_operate);


        //跳转到这个 activity时 接收数据 ， 当finish() 时把数据送回去同步
        dailyTasksBiao = new DailyTasksBiao();

        {
            int biaoid = getIntent().getIntExtra("biaoid",0);
            dailyTasksBiao = (DailyTasksBiao) getIntent().getSerializableExtra("biao");
        }

        ListView lv = findViewById(R.id.id_biao_dailyTasks_operarte_lv);

        lv.setAdapter(new dailyTasks_OperateAdapter(this));



    }
}