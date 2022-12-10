package com.example.xiaobenben.biao.DownTasksBiao;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.xiaobenben.R;

public class downTasksAdapter extends BaseAdapter {
    private Context context;
    private DownTasksBiao downTasksBiao;
    private downNewClickListener listener;
    public int sign = 1;

    public downTasksAdapter(Context context, DownTasksBiao downTasksBiao, downNewClickListener listener) {
        this.context = context;
        this.downTasksBiao = downTasksBiao;
        this.listener = listener;
    }

    public void addItem(){
        downTasksBiao.addDownTasksItem();
        notifyDataSetChanged();
        listener.callback_modify(downTasksBiao);
    }

    public void deleteItem(int i){
        downTasksBiao.deleteDownTasksItem(i);
        notifyDataSetChanged();
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
            view = LayoutInflater.from(context).inflate(R.layout.item_down_tasks_new, viewGroup, false);
        }

        TextView name_et = view.findViewById(R.id.id_biao_downTasks_new_item_name);
        TextView surplus_et = view.findViewById(R.id.id_biao_downTasks_new_item_surplus);
        ImageButton bnt = view.findViewById(R.id.id_biao_downTasks_new_item_delete);


        name_et.setText(downTasksBiao.getDownTasksItemList().get(i).getName());
        surplus_et.setText(downTasksBiao.getDownTasksItemList().get(i).getSurplus()+"");




        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(i);
                listener.callback_modify(downTasksBiao);
            }
        });






        return view;
    }

    public  static  int  strToInt( String  value,  int  defaultValue) {
        try  {
            return  Integer.valueOf(value);
        }  catch  (Exception e) {
            return  defaultValue;
        }
    }


    public static interface downNewClickListener{
        public void callback_modify(DownTasksBiao biao);
    }


}
