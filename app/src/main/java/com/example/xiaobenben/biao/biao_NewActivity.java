package com.example.xiaobenben.biao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaobenben.MainActivity;
import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.DailyTasksBiao.dailyTasksBiao_NewActivity;
import com.example.xiaobenben.biao.DailyTasksBiao.dailyTasksBiao_OperateActivity;
import com.example.xiaobenben.biao.DownTasksBiao.downTasksBiao_NewActivity;
import com.example.xiaobenben.biao.ScheduleTasksBiao.scheduleTasksBiao_NewActivity;
import com.example.xiaobenben.biao.TourTasksBiao.tourTasksBiao_NewActivity;
import com.example.xiaobenben.control.CircleImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class biao_NewActivity extends AppCompatActivity {

//    private String[] ss=new String[]{
//            "北京",
//            "上海",
//            "深圳",
//            "shanxi",
//            "yunc",
//            "ss"
//    };
    private String conSelect;
    private String biaoName;
    private List<String> list =new ArrayList<>();
    private List<String> list_introduce =new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biao_new);

        list.add("每日任务计划表");
        list.add("日程计划表");
        list.add("旅游计划表");
        list.add("倒数计划表");

        list_introduce.add("    每日任务计划表：该表适用于制定每天都计划做的一些事情，比如想要养成一些好习惯：早起，跑步等等");
        list_introduce.add("    日程计划表：该表适用于制定随时可能来到却不需要立刻完成的一些事情，比如被安排了N天后要做一件事情或者某件事要截止在N天前完成。");
        list_introduce.add("    旅游计划表： 该表适用于简单的制定将来想要旅行的计划，比如打算在某个地方安排一场毕业旅行");
        list_introduce.add("    倒数计划表：该表适用于制定一定时间内限制次数的一些事情，比如想要戒掉或缓解一些不健康的行为：吃高热量的夜宵，抽烟等等");



        conSelect = list.get(0);
        context = this;

        TextView introduce_tv = findViewById(R.id.id_biao_introduce_tv);
        Spinner spinner=findViewById(R.id.id_biao_new_spinner);
        BaseAdapter adapter =new MyAdapter();//adapter适配器
        spinner.setAdapter(adapter);

        //可以监听器看看
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择了不同的选项，调用这个
                conSelect = list.get(position);

                introduce_tv.setText(list_introduce.get(position));


                Toast.makeText(biao_NewActivity.this,list.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //在下拉选项种选择了本来选的东西，也就是说没改变选项，调用这个

            }
        });


        EditText et = findViewById(R.id.id_biao_new_et);


        CircleImageView sureimgbnt = findViewById(R.id.id_biao_new_sure_imgbnt);

        sureimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(conSelect.equals("每日任务计划表")){
                    Intent intent = new Intent(context, dailyTasksBiao_NewActivity.class);
                    intent.putExtra("biaoName", et.getText().toString());

                    ((Activity)context).startActivity(intent);
                    finish();
                }else if(conSelect.equals("倒数计划表")){
                    Intent intent = new Intent(context, downTasksBiao_NewActivity.class);
                    intent.putExtra("biaoName", et.getText().toString());

                    ((Activity)context).startActivity(intent);
                    finish();
                }else if(conSelect.equals("日程计划表")){
                    Intent intent = new Intent(context, scheduleTasksBiao_NewActivity.class);
                    intent.putExtra("biaoName", et.getText().toString());

                    ((Activity)context).startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(context, tourTasksBiao_NewActivity.class);
                    intent.putExtra("biaoName", et.getText().toString());

                    ((Activity)context).startActivity(intent);
                    finish();



                }


            }
        });


        CircleImageView back = findViewById(R.id.id_biao_new_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


//        //第二种方法
//        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item ,ss);
//        Spinner spinner2=findViewById(R.id.id_biao_new_spinner);
//        spinner2.setAdapter(adapter2);
//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //选择了不同的选项，调用这个
//                Toast.makeText(biao_NewActivity.this,ss[position],Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                //在下拉选项种选择了本来选的东西，也就是说没改变选项，调用这个
//
//            }
//        });

    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            //return ss.length;
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView=new TextView(biao_NewActivity.this);
            //textView.setText(ss[position]);
            textView.setText(list.get(position));
            return textView;
        }
    }


}