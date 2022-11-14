package com.example.xiaobenben.ben;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.control.CircleImageView;

import java.util.HashMap;

public class ben_OperateActivity extends AppCompatActivity {

    private ImageButton cancelimgbnt;
    private ImageButton sureimgbnt;


    public static FrameLayout fl;
    public static int i;
    public static ListView lv;
    public static Context context;
    public static Ben ben;

    public static boolean isMulChoice;
    public static HashMap<Integer, Boolean> ischeck;    //用于记录那些 id 被选中



    public static void changeAdapter(Ben b){
        ben = b;
        lv.setAdapter(new ben_OperateAdapter(ben,context,i));
    }


    public static void delRiji(){
        isMulChoice = true;
        fl.setVisibility(FrameLayout.VISIBLE);

        lv.setAdapter(new ben_OperateAdapter(ben,context,i));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben_operate);

        isMulChoice = false;
        ischeck = new HashMap<>();

        i = getIntent().getIntExtra("id",0);
        ben = (Ben) getIntent().getSerializableExtra("benname");

        context = this;



        fl = findViewById(R.id.id_ben_operate_del_fl);
        cancelimgbnt = findViewById(R.id.id_ben_operate_del_cancel_imgbnt);
        sureimgbnt = findViewById(R.id.id_ben_operate_del_sure_imgbnt);

        fl.setVisibility(FrameLayout.INVISIBLE);

        sureimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shanchu
                for(int i = ben.getRijiList().size() - 1; i >= 0; i--){
                    if(ischeck.get(i) != null){
                        ben.getRijiList().remove(i);
                    }
                }
                ischeck.clear();
                isMulChoice = false;
                fl.setVisibility(FrameLayout.INVISIBLE);
                lv.setAdapter(new ben_OperateAdapter(ben,context,i));
            }
        });


        cancelimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ischeck.clear();
                isMulChoice = false;
                fl.setVisibility(FrameLayout.INVISIBLE);
                lv.setAdapter(new ben_OperateAdapter(ben,context,i));
            }
        });



        TextView tv1 = findViewById(R.id.id_ben_operate_tv1);
        tv1.setText(ben.getName());


        lv = findViewById(R.id.id_ben_operate_lv);
        lv.setAdapter(new ben_OperateAdapter(ben,context,i));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int id, long l) {
                if(isMulChoice == true){
                    ischeck.put(id,true);
                    lv.setAdapter(new ben_OperateAdapter(ben,context,i));
                }else{
                    Intent intent = new Intent(context, ben_riji_ChangeActivity.class);
                    intent.putExtra("riji",ben.getRijiList().get(id));
                    intent.putExtra("benid",i);
                    intent.putExtra("i",id);
                    context.startActivity(intent);
                }

            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int id, long l) {
                isMulChoice = true;
                fl.setVisibility(FrameLayout.VISIBLE);

                ischeck.put(id,true);

                lv.setAdapter(new ben_OperateAdapter(ben,context,i));
                return false;
            }
        });



        CircleImageView backimgbnt = findViewById(R.id.id_ben_operate_back_imgbnt);
        backimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        CircleImageView newrjimgbnt = findViewById(R.id.id_ben_operate_newrj_imgbnt);
        newrjimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new riji
                Intent intent = new Intent(context, ben_riji_NewActivity.class);
                intent.putExtra("id",i);


                ((Activity)context).startActivityForResult(intent,1);

                //修改之后需要重新setadapter
            }
        });







    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra("123",ben);
        intent.putExtra("id",i);


        setResult(2,intent);

        super.onBackPressed();


        return;
    }
}