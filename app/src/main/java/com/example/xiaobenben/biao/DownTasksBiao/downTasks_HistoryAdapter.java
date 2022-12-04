package com.example.xiaobenben.biao.DownTasksBiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiaobenben.R;

import org.w3c.dom.Text;

public class downTasks_HistoryAdapter  extends BaseAdapter {
    private Context context;
    private DownTasksBiao downTasksBiao;

    public downTasks_HistoryAdapter(Context context, DownTasksBiao downTasksBiao) {
        this.context = context;
        this.downTasksBiao = downTasksBiao;
    }

    @Override
    public int getCount() {
        return downTasksBiao.getHistoryDownTasksItemList().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_down_tasks_history, viewGroup, false);
        }

        TextView time_tv = view.findViewById(R.id.id_biao_downTasks_history_item_time);
        TextView name_tv = view.findViewById(R.id.id_biao_downTasks_history_item_name);

        time_tv.setText("你在"+downTasksBiao.getHistoryDownTasksItemList().get(i).getBiaoTime().getDate());
        name_tv.setText("消耗了一次"+downTasksBiao.getHistoryDownTasksItemList().get(i).getName());




        return view;
    }
}
