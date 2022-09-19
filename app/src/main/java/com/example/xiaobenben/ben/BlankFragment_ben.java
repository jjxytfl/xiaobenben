package com.example.xiaobenben.ben;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.xiaobenben.R;

import java.util.ArrayList;
import java.util.List;



//这个fragment 不会 销毁 除非 mainactivity 销毁

public class BlankFragment_ben extends Fragment {

    private static ListView lv;


    private static  List<Ben> benList = new ArrayList<>();
    public static Context context;


    public static List<Ben> getBenList() {
        return benList;
    }

    public static void delBen(int i){
        benList.remove(i);
        lv.setAdapter(new benAdapter(benList,context));
    }

    public static void addBen(Ben ben){
        benList.add(benList.size()-1,ben);
        lv.setAdapter(new benAdapter(benList,context));
    }

    public static void changeBen(Ben ben, int benid){
        benList.set(benid,ben);
        lv.setAdapter(new benAdapter(benList,context));
    }


    public static void addRiji(int i , Riji rj){
        benList.get(i).getRijiList().add(rj);
        ben_OperateActivity.changeAdapter(benList.get(i));
    }

    public static void changeRiji(int benid, int i, Riji rj){

        benList.get(benid).getRijiList().set(i ,rj);
        ben_OperateActivity.changeAdapter(benList.get(benid));
    }


    public BlankFragment_ben() {
        // Required empty public constructor

    }


    public BlankFragment_ben(List<Ben> bl) {
        benList = bl;
        // Required empty public constructor
    }






    // TODO: Rename and change types and number of parameters
    public static BlankFragment_ben newInstance(List<Ben> benList) {
        BlankFragment_ben fragment = new BlankFragment_ben(benList);
        Bundle args = new Bundle();

        //chuandi shu ju

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_blank_ben, container, false);
        lv = inflate.findViewById(R.id.id_ben_lv);



        lv.setAdapter(new benAdapter(benList,context));


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("确认删除？");
//                builder.setTitle("提示");
//                builder.create().show();






                Intent intent = new Intent(context, ben_ChangeActivity.class);
                intent.putExtra("ben",benList.get(i));
                intent.putExtra("benid",i);

                context.startActivity(intent);

                return false;
            }
        });


        return inflate;

    }
}