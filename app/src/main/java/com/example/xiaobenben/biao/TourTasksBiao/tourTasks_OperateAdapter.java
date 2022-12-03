package com.example.xiaobenben.biao.TourTasksBiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaobenben.R;

public class tourTasks_OperateAdapter  extends BaseAdapter {
    private TourTasksBiao tourTasksBiao;
    private Context context;
    private tourOperateClickListener listener;


    public tourTasks_OperateAdapter(TourTasksBiao tourTasksBiao, Context context, tourOperateClickListener listener) {
        this.tourTasksBiao = tourTasksBiao;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return tourTasksBiao.getTourTasksInComItems().size();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_tour_tasks_operate, viewGroup, false);
        }

        TextView places_tv = view.findViewById(R.id.id_biao_tourTasks_operate_item_places);
        TextView date_tv = view.findViewById(R.id.id_biao_tourTasks_operate_item_date);
        TextView expenses_tv = view.findViewById(R.id.id_biao_tourTasks_operate_item_expenses);

        Button detail_bnt = view.findViewById(R.id.id_biao_tourTasks_operate_item_detail_bnt);
        Button com_bnt = view.findViewById(R.id.id_biao_tourTasks_operate_item_com_bnt);


        places_tv.setText(tourTasksBiao.getTourTasksInComItems().get(i).getPlaces());
        date_tv.setText(tourTasksBiao.getTourTasksInComItems().get(i).getDate());
        expenses_tv.setText(tourTasksBiao.getTourTasksInComItems().get(i).getExpenses());


        detail_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.callback_mod(i);
            }
        });

        com_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.callback_com(i);
            }
        });



        return view;
    }





    public static interface tourOperateClickListener{
        public void callback_com(int i);
        public void callback_mod(int i);
    }
}
