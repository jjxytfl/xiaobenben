package com.example.xiaobenben.biao.ScheduleTasksBiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaobenben.R;

import org.w3c.dom.Text;

public class scheduleTasks_OperateAdapter extends BaseAdapter {
    private Context context;
    private ScheduleTasksBiao scheduleTasksBiao;
    private scheduleOperateClickListener listener;

    public scheduleTasks_OperateAdapter(Context context, ScheduleTasksBiao scheduleTasksBiao, scheduleOperateClickListener listener) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_schedule_tasks_operate, viewGroup, false);
        }
        TextView task_tv = view.findViewById(R.id.id_biao_scheduleTasks_operate_item_task);
        TextView date_tv = view.findViewById(R.id.id_biao_scheduleTasks_operate_item_date);

        Button sure_bnt = view.findViewById(R.id.id_biao_scheduleTasks_operate_item_sure);

        task_tv.setText(scheduleTasksBiao.getScheduleTasksInComItems().get(i).task);
        //不太规范
        date_tv.setText(scheduleTasksBiao.getScheduleTasksInComItems().get(i).date+scheduleTasksBiao.getScheduleTasksInComItems().get(i).time);




        sure_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确定
                scheduleTasksBiao.addScheduleTasksComItem(i,"06-06","00:00");
                notifyDataSetChanged();
            }
        });



        return view;
    }




    public static interface scheduleOperateClickListener{
        public void callback_consume(int i,boolean completion);  //自行配置参数  需要传递到activity的值
        public void callback_Remarks(int i,String remark);
    }
}
