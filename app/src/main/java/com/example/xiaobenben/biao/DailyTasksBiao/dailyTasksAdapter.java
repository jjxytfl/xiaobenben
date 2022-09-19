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
        Log.d("12345", "getView: sadasdasd");


        EditText et1 = view.findViewById(R.id.id_biao_dailyTasks_item_name_et1);
        EditText et2 = view.findViewById(R.id.id_biao_dailyTasks_item_name_et2);
        EditText et3 = view.findViewById(R.id.id_biao_dailyTasks_item_name_et3);


        et1.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getName());
        et2.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getDetails());
        et3.setText(dailyTasksBiao.getDailyTasksItemList().get(i).getTime());


        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dailyTasksBiao_NewActivity.dailyTasksBiao.getDailyTasksItemList().get(i).setName(editable.toString());
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dailyTasksBiao_NewActivity.dailyTasksBiao.getDailyTasksItemList().get(i).setTime(editable.toString());
            }
        });


        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                dailyTasksBiao_NewActivity.dailyTasksBiao.getDailyTasksItemList().get(i).setDetails(editable.toString());
            }
        });










        //所有的内容都可编辑，为新建栏按钮设置监听，当最后一栏的名字没有填写时不处理


        return view;
    }
}
