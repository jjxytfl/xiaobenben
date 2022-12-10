package com.example.xiaobenben.biao.DailyTasksBiao;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.xiaobenben.R;

import java.util.List;

public class dailyTasksAdapter extends BaseAdapter{
    private Context context;
    private DailyTasksBiao dailyTasksBiao;


    public dailyTasksAdapter(Context context,DailyTasksBiao dailyTasksBiao){
        this.context = context;
        this.dailyTasksBiao = dailyTasksBiao;
    }

    @Override
    public int getCount() {
        return dailyTasksBiao.getDailyTasksItemList().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_daily_tasks, viewGroup, false);

        }


        TextView et1 = view.findViewById(R.id.id_biao_dailyTasks_item_name_et1);
        TextView et2 = view.findViewById(R.id.id_biao_dailyTasks_item_time_et2);
        TextView et3 = view.findViewById(R.id.id_biao_dailyTasks_item_detail_et3);
        ImageButton del = view.findViewById(R.id.id_biao_dailyTasks_item_detail_del);


        et1.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getName());
        et2.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getTime());
        et3.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getDetails());

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailyTasksBiao.getDailyTasksItemList().remove(i);
                notifyDataSetChanged();
            }
        });



        //所有的内容都可编辑，为新建栏按钮设置监听，当最后一栏的名字没有填写时不处理


        return view;
    }
}
