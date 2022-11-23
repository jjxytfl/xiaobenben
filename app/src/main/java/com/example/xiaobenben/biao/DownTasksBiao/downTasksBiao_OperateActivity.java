package com.example.xiaobenben.biao.DownTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.xiaobenben.R;

public class downTasksBiao_OperateActivity extends AppCompatActivity {

    private Context context;
    public DownTasksBiao downTasksBiao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_tasks_biao_operate);

        context = this;


        ListView lv = findViewById(R.id.id_biao_downTasks_lv);


    }
}