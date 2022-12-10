package com.example.xiaobenben;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiaobenben.biao.DailyTasksBiao.dailyTasksBiao_NewActivity;
import com.example.xiaobenben.wo.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment_me#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment_me extends Fragment {

    public static Context context;


    View root;


    public BlankFragment_me() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment_me.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment_me newInstance(String param1, String param2) {
        BlankFragment_me fragment = new BlankFragment_me();
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
        if(root == null){
            root = inflater.inflate(R.layout.fragment_blank_me, container, false);
        }

        initView();
        return root;
    }

    private void initView() {
        TextView tv = root.findViewById(R.id.id_wo_dl);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, LoginActivity.class );
                startActivity(intent);
            }
        });


//        Intent intent = new Intent(context, dailyTasksBiao_NewActivity.class);
//        context.startActivity(intent);
    }
}