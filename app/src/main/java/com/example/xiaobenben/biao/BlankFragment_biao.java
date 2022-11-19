package com.example.xiaobenben.biao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.DailyTasksBiao.dailyTasksBiao_NewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment_biao#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment_biao extends Fragment {


    private static ListView biao_lv;
    public static Context context;
    public static List<Biao> biaoList = new ArrayList<>();

    public static void addBiao(Biao biao){
        biaoList.add(biao);
        biao_lv.setAdapter(new biaoAdapter(context,biaoList));
    }

    public static void modify(int i,Biao biao){
        biaoList.set(i,biao);
        biao_lv.setAdapter(new biaoAdapter(context,biaoList));
    }




    public BlankFragment_biao(List<Biao> bl) {
        biaoList = bl;
    }

    public static BlankFragment_biao newInstance(String param1, String param2) {
        BlankFragment_biao fragment = new BlankFragment_biao(new ArrayList<>());
        Bundle args = new Bundle();

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
        View view = inflater.inflate(R.layout.fragment_blank_biao, container, false);

        biao_lv = view.findViewById(R.id.id_biao_lv);
        biao_lv.setAdapter(new biaoAdapter(context,biaoList));


        biao_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                return false;
            }
        });


        TextView addImgbnt = view.findViewById(R.id.id_biao_add_imgbnt);
        addImgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, biao_NewActivity.class);
                context.startActivity(intent);
            }
        });


        return view;
    }
}