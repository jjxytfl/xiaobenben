package com.example.xiaobenben;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaobenben.ben.ben_ChangeActivity;
import com.example.xiaobenben.biao.DailyTasksBiao.dailyTasksBiao_newActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mTextString;

    int selectColour;

    View rootview;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1,int sc) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM2,sc);
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextString = getArguments().getString(ARG_PARAM1);
            selectColour = getArguments().getInt(ARG_PARAM2);
            Log.d("TAG", "onCreate: " + mTextString);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootview == null){
            rootview = inflater.inflate(R.layout.fragment_blank, container, false);

        }
        initView();







        return rootview;

    }

    private void initView() {
        TextView textView = rootview.findViewById(R.id.text);
        textView.setText(mTextString);

        FrameLayout ll = rootview.findViewById(R.id.framegment);

        switch (selectColour){
            case 0:
                ll.setBackgroundColor(0xFF00FF00);
                break;
            case 1:
                ll.setBackgroundColor(0xFFFFFF00);
                break;
            case 2:
                ll.setBackgroundColor(0xFF00FFFF);
                break;
            case 3:
                ll.setBackgroundColor(0xFF000000);
                break;
            default:
                break;

        }


    }
}