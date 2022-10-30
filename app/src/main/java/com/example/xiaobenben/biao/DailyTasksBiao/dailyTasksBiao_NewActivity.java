package com.example.xiaobenben.biao.DailyTasksBiao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.BlankFragment_biao;

public class dailyTasksBiao_NewActivity extends AppCompatActivity {
    public static DailyTasksBiao dailyTasksBiao = new DailyTasksBiao();

    public static void modify(DailyTasksBiao dtb){
        dailyTasksBiao = dtb;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks_biao_new);

        dailyTasksBiao = new DailyTasksBiao();

        Context context = this;
        ListView lv = findViewById(R.id.id_biao_dailyTasks_new_lv);
        lv.setAdapter(new dailyTasksAdapter(this, dailyTasksBiao));


        ImageButton sureImgbnt = findViewById(R.id.id_biao_dailyTasks_new_sure_imgbnt);
        sureImgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BlankFragment_biao.addBiao(dailyTasksBiao);
                finish();
            }
        });



        ImageButton addImgbnt = findViewById(R.id.id_biao_dailyTasks_new_add_imgbnt);
        addImgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断最后一栏是否为空 ， 增加之后需要重新加载adapter
                //10-30 上述方法避免不了名字为空 应该在为空时给出提示 或强制添加 name
                if(!dailyTasksBiao.getDailyTasksItemList().get(dailyTasksBiao.getDailyTasksItemList().size()-1).getName().equals("")){


                    dailyTasksBiao.addDailyTasksItem("Name","Time","Details");
                    lv.setAdapter(new dailyTasksAdapter(context, dailyTasksBiao));
                }else{
                    //弹出 提示： 请输入name
                    Toast.makeText(dailyTasksBiao_NewActivity.this, "请填写计划名字", Toast.LENGTH_LONG).show();
                    //可以考虑吧焦点给到对应exittext
                }



            }
        });



        ImageButton backImgbnt = findViewById(R.id.id_biao_dailyTasks_new_back_imgbnt);
        backImgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




    }
}