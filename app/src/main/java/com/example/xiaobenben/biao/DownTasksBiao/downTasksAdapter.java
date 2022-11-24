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

        EditText name_et = view.findViewById(R.id.id_biao_downTasks_new_item_name);
        EditText surplus_et = view.findViewById(R.id.id_biao_downTasks_new_item_surplus);
        ImageButton bnt = view.findViewById(R.id.id_biao_downTasks_new_item_delete);

        if(i == getCount() - 1 && sign == 1){
            sign--;
        }else{
            name_et.setText(downTasksBiao.getDownTasksItemList().get(i).getName());
            surplus_et.setText(downTasksBiao.getDownTasksItemList().get(i).getSurplus()+"");

        }

        name_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                downTasksBiao.getDownTasksItemList().get(i).setName(editable.toString());
                listener.callback_modify(downTasksBiao);
            }
        });


        surplus_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()<1){
                    return;
                }
                downTasksBiao.getDownTasksItemList().get(i).setSurplus(strToInt(editable.toString(),10));
                listener.callback_modify(downTasksBiao);
            }
        });


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
