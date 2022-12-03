package com.example.xiaobenben.biao.TourTasksBiao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.xiaobenben.R;

public class tourTasksAdapter   extends BaseAdapter {
    private TourTasksBiao tourTasksBiao;
    private Context context;
    private tourNewClickListener listener;

    public tourTasksAdapter(TourTasksBiao tourTasksBiao, Context context, tourNewClickListener listener) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_tour_tasks_new, viewGroup, false);
        }

        TextView places_tv = view.findViewById(R.id.id_biao_tourTasks_new_item_places);
        TextView date_tv = view.findViewById(R.id.id_biao_tourTasks_new_item_date);
        TextView expenses_tv = view.findViewById(R.id.id_biao_tourTasks_new_item_expenses);

        Button detail_bnt = view.findViewById(R.id.id_biao_tourTasks_new_item_detail_bnt);
        Button del_bnt = view.findViewById(R.id.id_biao_tourTasks_new_item_del_bnt);


        places_tv.setText(tourTasksBiao.getTourTasksInComItems().get(i).getPlaces());
        date_tv.setText(tourTasksBiao.getTourTasksInComItems().get(i).getDate());
        expenses_tv.setText(tourTasksBiao.getTourTasksInComItems().get(i).getExpenses());

        detail_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.callback_mod(i);
            }
        });


        del_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.callback_del(i);
            }
        });

        return view;
    }


    public static interface tourNewClickListener{
        public void callback_del(int i);
        public void callback_mod(int i);
    }
}
