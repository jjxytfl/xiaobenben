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
import com.example.xiaobenben.control.CircleImageView;

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
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biao_new);

        list.add("每日任务计划表");
        list.add("日程计划表");
        list.add("旅游计划表");
        list.add("倒数计划表");
        
        conSelect = list.get(0);
        context = this;

        Spinner spinner=findViewById(R.id.id_biao_new_spinner);
        BaseAdapter adapter =new MyAdapter();//adapter适配器
        spinner.setAdapter(adapter);

        //可以监听器看看
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择了不同的选项，调用这个
                conSelect = list.get(position);
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
                }else{
                    Intent intent = new Intent(context, downTasksBiao_NewActivity.class);
                    intent.putExtra("biaoName", et.getText().toString());

                    ((Activity)context).startActivity(intent);
                    finish();
                }


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