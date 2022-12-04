package com.example.xiaobenben.biao.DownTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.BlankFragment_biao;
import com.example.xiaobenben.biao.DailyTasksBiao.DailyTasksBiao;
import com.example.xiaobenben.biao.TourTasksBiao.tourTasksBiao_OperateActivity;

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
//        downTasksBiao = new DownTasksBiao();
//        downTasksBiao.addDownTasksItem("吸烟",10);
//        downTasksBiao.addDownTasksItem("喝酒",15);
//        downTasksBiao.addDownTasksItem("ck",12);
//        downTasksBiao.addDownTasksItem("pc",10);

        int biaoid;
        {
            biaoid = getIntent().getIntExtra("biaoid",0);
            downTasksBiao = (DownTasksBiao) getIntent().getSerializableExtra("biao");
        }


        TextView history_tv = findViewById(R.id.id_biao_downTasks_operate_history_tv);
        history_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, downTasksBiao_HistoryActivity.class);
                intent.putExtra("biao", downTasksBiao);
                intent.putExtra("biaoid", biaoid);

                ((Activity)context).startActivity(intent);
            }
        });


        ListView lv = findViewById(R.id.id_biao_downTasks_lv);
        lv.setAdapter(adapter = new downTasks_OperateAdapter(context, downTasksBiao, listener = new downTasks_OperateAdapter.downOperateClickListener() {
            @Override
            public void callback_consume(int i, boolean completion) {

            }

            @Override
            public void callback_Remarks(int i, String remark) {

            }
        }));


        Button pressed_bnt = findViewById(R.id.id_biao_downTasks_operate_preassed_bnt);
        pressed_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment_biao.modify(biaoid,downTasksBiao);
                onBackPressed();
            }
        });




        Button sure_bnt = findViewById(R.id.id_biao_downTasks_operate_sure_bnt);
        sure_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment_biao.modify(biaoid,downTasksBiao);
                onBackPressed();
            }
        });

    }

}