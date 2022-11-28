package com.example.xiaobenben.biao.ScheduleTasksBiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.DownTasksBiao.DownTasksBiao;

public class scheduleTasksAdapter  extends BaseAdapter {
    private Context context;
    private ScheduleTasksBiao scheduleTasksBiao;
    private scheduleNewClickListener listener;

    public scheduleTasksAdapter(Context context, ScheduleTasksBiao scheduleTasksBiao, scheduleNewClickListener listener) {
        this.context = context;
        this.scheduleTasksBiao = scheduleTasksBiao;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return scheduleTasksBiao.getScheduleTasksInComItems().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_schedule_tasks_new, viewGroup, false);
        }
        TextView task_tv = view.findViewById(R.id.id_biao_scheduleTasks_new_item_task);
        TextView date_tv = view.findViewById(R.id.id_biao_scheduleTasks_new_item_date);
        ImageView del_img = view.findViewById(R.id.id_biao_scheduleTasks_new_item_delete);

        task_tv.setText(scheduleTasksBiao.getScheduleTasksInComItems().get(i).task);
        date_tv.setText(scheduleTasksBiao.getScheduleTasksInComItems().get(i).date);


        del_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.callback_del(i);
            }
        });



        return view;
    }



    public static interface scheduleNewClickListener{
        public void callback_del(int i);
    }
}
