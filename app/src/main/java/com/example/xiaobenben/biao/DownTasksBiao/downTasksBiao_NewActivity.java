package com.example.xiaobenben.biao.DownTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.BlankFragment_biao;

public class downTasksBiao_NewActivity extends AppCompatActivity {

    private Context context;
    private DownTasksBiao downTasksBiao;
    private downTasksAdapter.downNewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_tasks_biao_new);

        downTasksBiao = new DownTasksBiao();
        context = this;


        String biaoName = getIntent().getStringExtra("biaoName");
        downTasksBiao.setBiaoName(biaoName);

        ListView lv = findViewById(R.id.id_biao_downTasks_new_lv);

        lv.setAdapter(new downTasksAdapter(context, downTasksBiao, listener = new downTasksAdapter.downNewClickListener() {
            @Override
            public void callback_modify(DownTasksBiao biao) {
                downTasksBiao = biao;
            }
        }));



        Button bnt = findViewById(R.id.id_biao_downTasks_new_add_bnt);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downTasksBiao.addDownTasksItem();
                downTasksAdapter adapter = new downTasksAdapter(context,downTasksBiao,listener);
                adapter.sign = 1;
                lv.setAdapter(adapter);
            }
        });


        Button sure_bnt = findViewById(R.id.id_biao_downTasks_new_sure_bnt);
        sure_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment_biao.addBiao(downTasksBiao);
                finish();
            }
        });







    }
}