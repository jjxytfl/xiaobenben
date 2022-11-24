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
    private downTasks_OperateAdapter adapter;
    private downTasks_OperateAdapter.downOperateClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_tasks_biao_operate);

        context = this;
        downTasksBiao = new DownTasksBiao();
        downTasksBiao.addDownTasksItem("吸烟",10);
        downTasksBiao.addDownTasksItem("喝酒",15);
        downTasksBiao.addDownTasksItem("ck",12);
        downTasksBiao.addDownTasksItem("pc",10);



        ListView lv = findViewById(R.id.id_biao_downTasks_lv);
        lv.setAdapter(adapter = new downTasks_OperateAdapter(context, downTasksBiao, listener = new downTasks_OperateAdapter.downOperateClickListener() {
            @Override
            public void callback_consume(int i, boolean completion) {

            }

            @Override
            public void callback_Remarks(int i, String remark) {

            }
        }));


    }
}