package com.example.xiaobenben.biao.DownTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.xiaobenben.R;

public class downTasksBiao_HistoryActivity extends AppCompatActivity {
    private DownTasksBiao downTasksBiao;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_tasks_biao_history);
        context = this;
        downTasksBiao = new DownTasksBiao();

        int biaoid;
        {
            biaoid = getIntent().getIntExtra("biaoid",0);
            downTasksBiao = (DownTasksBiao) getIntent().getSerializableExtra("biao");
        }



        ListView lv = findViewById(R.id.id_biao_downTasks_history_lv);
        lv.setAdapter(new downTasks_HistoryAdapter(context,downTasksBiao));

    }
}