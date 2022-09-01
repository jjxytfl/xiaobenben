package com.example.xiaobenben.ben;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.ben.Ben;
import com.example.xiaobenben.ben.ben_OperateActivity;

public class ben_OperateAdapter extends BaseAdapter {
    private Ben ben;
    private int benid;
    private Context context;



    public ben_OperateAdapter(Ben ben, Context context, int benid) {
        this.ben = ben;
        this.context = context;
        this.benid = benid;
    }


    @Override
    public int getCount() {
        return ben_OperateActivity.ben.getRijiList().size();
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
        //Log.d("1234", "getView: " + ben_OperateActivity.ischeck.size());
        if(view == null){
            view = LayoutInflater.from(ben_OperateActivity.context).inflate(R.layout.item_ben_riji,viewGroup,false);
        }
        TextView tv1 = view.findViewById(R.id.id_ben_riji_tv1);
        TextView tv2 = view.findViewById(R.id.id_ben_riji_tv2);
        TextView tv3 = view.findViewById(R.id.id_ben_riji_tv3);

        CheckBox ceb = view.findViewById(R.id.id_ben_riji_cb);

        if(ben_OperateActivity.isMulChoice == true){
            ceb.setVisibility(CheckBox.VISIBLE);
        }else{
            ceb.setVisibility(CheckBox.INVISIBLE);
        }

        if(ben_OperateActivity.ischeck.get(i) != null){
            ceb.setChecked(true);
        }else{
            ceb.setChecked(false);
        }


        ceb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ceb.isChecked() == true) {
                    ceb.setChecked(true);
                    ben_OperateActivity.ischeck.put(i,true);
                }else{
                    ceb.setChecked(false);
                    ben_OperateActivity.ischeck.remove(i);
                }

            }
        });


        tv1.setText(ben_OperateActivity.ben.getRijiList().get(i).getJianjie1());
        tv2.setText(ben_OperateActivity.ben.getRijiList().get(i).getJianjie2());
        tv3.setText(ben_OperateActivity.ben.getRijiList().get(i).getContent());

//        view.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                ben_OperateActivity.delRiji();
//
//                Log.d("1234", "onLongClick: ");
//
//                return false;
//            }
//        });
//
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(ben_OperateActivity.isMulChoice == true){
//                    ben_OperateActivity.ischeck.put(i,true);
//                }else{
//                    Intent intent = new Intent(context, ben_riji_ChangeActivity.class);
//                    intent.putExtra("riji",rijiList.get(i));
//                    intent.putExtra("benid",benid);
//                    intent.putExtra("i",i);
//                    context.startActivity(intent);
//                }
//
//
//
//            }
//        });



        return view;
    }
}
