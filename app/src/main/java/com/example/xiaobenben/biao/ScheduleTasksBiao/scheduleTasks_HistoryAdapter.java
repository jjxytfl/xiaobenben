package com.example.xiaobenben.biao.ScheduleTasksBiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiaobenben.R;

public class scheduleTasks_HistoryAdapter extends BaseAdapter{
    private ScheduleTasksBiao scheduleTasksBiao;
    private Context context;


    public scheduleTasks_HistoryAdapter(ScheduleTasksBiao scheduleTasksBiao, Context context) {
        this.scheduleTasksBiao = scheduleTasksBiao;
        this.context = context;
    }

    @Override
    public int getCount() {
        return scheduleTasksBiao.getScheduleTasksComItems().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_schedule_tasks_history, viewGroup, false);
        }

        TextView time_tv = view.findViewById(R.id.id_biao_scheduleTasks_history_item_time);
        TextView name_tv = view.findViewById(R.id.id_biao_scheduleTasks_history_item_name);


        time_tv.setText("你在"+scheduleTasksBiao.getScheduleTasksComItems().get(i).getDate());
        name_tv.setText("完成了”"+scheduleTasksBiao.getScheduleTasksComItems().get(i).getTask()+"“日程");




        return view;
    }
}
