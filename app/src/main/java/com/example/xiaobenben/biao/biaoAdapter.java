package com.example.xiaobenben.biao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.ben.ben_ChangeActivity;
import com.example.xiaobenben.biao.DailyTasksBiao.dailyTasksBiao_OperateActivity;
import com.example.xiaobenben.biao.DownTasksBiao.downTasksBiao_OperateActivity;

import java.util.List;

public class biaoAdapter extends BaseAdapter {
    private Context context;
    private List<Biao> biaoList;


    public biaoAdapter(Context context, List<Biao> biaoList) {
        this.context = context;
        this.biaoList = biaoList;
    }

    @Override
    public int getCount() {
        return biaoList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_biao,viewGroup,false);
        }

        TextView tv1 = view.findViewById(R.id.id_biao_item_tv1);
        tv1.setText(biaoList.get(i).getBiaoName());



        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //根据 表类型的不同 跳转到不同的activity设计不同的
                if(biaoList.get(i).getBiaoType().equals("每日任务计划表")){

                    Intent intent = new Intent(context, dailyTasksBiao_OperateActivity.class);
                    intent.putExtra("biao", biaoList.get(i));
                    intent.putExtra("biaoid", i);

                    ((Activity)context).startActivity(intent);
                }else{
                    Intent intent = new Intent(context, downTasksBiao_OperateActivity.class);
                    intent.putExtra("biao", biaoList.get(i));
                    intent.putExtra("biaoid", i);

                    ((Activity)context).startActivity(intent);

                }

            }
        });


        return view;
    }
}
