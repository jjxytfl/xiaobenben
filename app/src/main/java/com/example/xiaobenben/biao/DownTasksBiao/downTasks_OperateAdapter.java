package com.example.xiaobenben.biao.DownTasksBiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaobenben.R;

public class downTasks_OperateAdapter extends BaseAdapter {
    private Context context;
    private DownTasksBiao downTasksBiao;
    private downOperateClickListener listener;

    public downTasks_OperateAdapter(Context context, DownTasksBiao downTasksBiao, downOperateClickListener listener) {
        this.context = context;
        this.downTasksBiao = downTasksBiao;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return downTasksBiao.getDownTasksItemList().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_down_tasks_operate, viewGroup, false);
        }

        TextView name_tv = view.findViewById(R.id.id_biao_downTasks_item_name);
        TextView surplus_tv = view.findViewById(R.id.id_biao_downTasks_item_surplus);
        Button bnt = view.findViewById(R.id.id_biao_downTasks_item_bnt);


        name_tv.setText(downTasksBiao.getDownTasksItemList().get(i).getName());
        surplus_tv.setText(downTasksBiao.getDownTasksItemList().get(i).getSurplus()+"");
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downTasksBiao.getDownTasksItemList().get(i).consume();
                surplus_tv.setText(downTasksBiao.getDownTasksItemList().get(i).getSurplus()+"");
                listener.callback_consume(i,true);
            }
        });


        return view;
    }


    public static interface downOperateClickListener{
        public void callback_consume(int i,boolean completion);  //自行配置参数  需要传递到activity的值
        public void callback_Remarks(int i,String remark);
    }


}
