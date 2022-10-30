package com.example.xiaobenben.biao.DailyTasksBiao;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.xiaobenben.R;

import java.util.ArrayList;

public class dailyTasks_OperateAdapter extends BaseAdapter {
    private Context context;
    private DailyTasksBiao dailyTasksBiao;
    private addClickListener listener;



    public static interface addClickListener{
        public void addClick(int position,boolean isok);  //自行配置参数  需要传递到activity的值
    }

    public void setaddClicklistener(addClickListener listener){
        this.listener = listener;
    }



    public dailyTasks_OperateAdapter(Context context, DailyTasksBiao dailyTasksBiao , addClickListener listener) {
        this.context = context;
        this.dailyTasksBiao = dailyTasksBiao;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return dailyTasksBiao_OperateActivity.dailyTasksBiao.getDailyTasksItemList().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_daily_tasks_operate, viewGroup, false);
        }

        TextView name_tv1 = view.findViewById(R.id.id_biao_dailyTasks_operarte_item_tv1);
        TextView time_tv2 = view.findViewById(R.id.id_biao_dailyTasks_operarte_item_tv2);
        ImageView completion_imgbnt3 = view.findViewById(R.id.id_biao_dailyTasks_operarte_item_imgbnt3);
        EditText details_et4 = view.findViewById(R.id.id_biao_dailyTasks_operarte_item_et4);


        name_tv1.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getName());
        time_tv2.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getTime());
        completion_imgbnt3.setSelected(dailyTasksBiao.getDailyTasksItemList().get(i).isCompletion());
        details_et4.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getRemarks());





        details_et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //改变后
                dailyTasksBiao.getDailyTasksItemList().get(i).setRemarks(editable.toString());

                dailyTasksBiao_OperateActivity.modify(dailyTasksBiao);
            }
        });



        completion_imgbnt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //监听图片，当被点击时切换  顺序  客观原因未完成 完成 主观原因未完成    可以通过当前状态进行判断  ,状态字符串的格式可以用R.string.来处理

                if(completion_imgbnt3.isSelected() == true){
                    completion_imgbnt3.setSelected(false);
                }else{
                    completion_imgbnt3.setSelected(true);
                }
                //
                listener.addClick(i,completion_imgbnt3.isSelected());


            }
        });


        return view;
    }
}
