package com.example.xiaobenben.ben;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.ben.Ben;
import com.example.xiaobenben.ben.ben_NewActivity;
import com.example.xiaobenben.ben.ben_OperateActivity;

import java.util.List;

public class benAdapter extends BaseAdapter {
    private List<Ben> benList;
    private Context context;

    public benAdapter(List<Ben> benList, Context context) {
        this.benList = benList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return benList.size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_ben,viewGroup,false);
        }

        ImageView img = view.findViewById(R.id.id_item_ben);
//        if (i % 2 == 0) {
//            img.setBackgroundColor(0xffff0000);
//        } else {
//            img.setBackgroundColor(0xff00ffff);
//        }

        TextView tv = view.findViewById(R.id.id_ben_tv);
        tv.setText(benList.get(i).getName());


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == getCount() - 1){
                    //新建日记本
                    Intent intent = new Intent(context, ben_NewActivity.class);

                    ((Activity)context).startActivity(intent);
                }else{
                    //打开日记本
                    Intent intent = new Intent(context, ben_OperateActivity.class);
                    intent.putExtra("benname",benList.get(i));
                    intent.putExtra("id",i);

                    ((Activity)context).startActivityForResult(intent,1);

                }

            }
        });
        return view;
    }





}
