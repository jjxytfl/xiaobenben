package com.example.xiaobenben;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xiaobenben.ben.Ben;
import com.example.xiaobenben.ben.BlankFragment_ben;
import com.example.xiaobenben.biao.BlankFragment_biao;
import com.example.xiaobenben.wo.LoginActivity;
import com.example.xiaobenben.zhong.ClockActivity;
import com.example.xiaobenben.zhong.Tomato.BlankFragment_zhong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager2 viewPager;



    private LinearLayout ll1,ll2,ll3,ll4,llcur;
    private ImageView iv1,iv2,iv3,iv4,ivcur;

    private List<Ben> benList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //设置返回按键监听
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        */




        initPager();
        initTabView();




    }

    public void refreshBenlist(List<Ben> bl) {
        //跳转
        this.benList = bl;
    }


    private void initTabView() {
        ll1=findViewById(R.id.id_tab_weixin);
        ll2=findViewById(R.id.id_tab_weixin2);
        ll3=findViewById(R.id.id_tab_weixin3);
        ll4=findViewById(R.id.id_tab_weixin4);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);


        iv1 = findViewById(R.id.tab_iv_weixin);
        iv2 = findViewById(R.id.tab_iv_weixin2);
        iv3 = findViewById(R.id.tab_iv_weixin3);
        iv4 = findViewById(R.id.tab_iv_weixin4);

        llcur = ll1;
        llcur.setBackgroundColor(0xaf00ff00);



        iv1.setSelected(true);
        ivcur=iv1;

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);


        if(requestCode==1 && resultCode==2){
            Ben  ben = (Ben)data.getSerializableExtra("123");
            int i = data.getIntExtra("id",0);
            //修改此处list<Ben>.get(i)的内容即可



            benList.set(i,ben);
        }

    }

    private void initPager() {
        viewPager = findViewById(R.id.viewPager);

        {
            BlankFragment_ben.context = this;
            BlankFragment_biao.context = this;
            BlankFragment_me.context = this;
            BlankFragment_zhong.context = this;
        }

        ArrayList<Fragment> fragments= new ArrayList<>();

        Context context = this;

        FileCacheUtil fileCacheUtil = new FileCacheUtil();
        //fileCacheUtil.qCloudDownload_benList(context);
        benList = new ArrayList();
        //benList = fileCacheUtil.read_benlist(this);
        {
            //benlist
            benList.add(new Ben("青蛙王子"));
            benList.add(new Ben("小天使"));
            benList.add(new Ben("大头子弹"));
            benList.add(new Ben("新建日记本"));

        }

        fragments.add(BlankFragment_ben.newInstance(benList));
        fragments.add(BlankFragment_biao.newInstance("txk","321"));
        fragments.add(BlankFragment_zhong.newInstance("27","2"));
        fragments.add(BlankFragment_me.newInstance("67867","me"));

        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), getLifecycle(), fragments);

        viewPager.setAdapter(pagerAdapter);



        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if(position == 3){
                    refreshBenlist(BlankFragment_ben.getBenList());
                    FileCacheUtil fileCacheUtil = new FileCacheUtil();
                    fileCacheUtil.write_benlist(context,benList);
                    //fileCacheUtil.qCloudUpload_benList(context,benList);



                }

                changeTab(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        ivcur.setSelected(false);
        llcur.setBackgroundColor(0x3f00ff00);
        switch (position){
            case R.id.id_tab_weixin:
                viewPager.setCurrentItem(0);
            case 0:
                iv1.setSelected(true);
                ivcur=iv1;
                llcur = ll1;
                break;
            case 1:
            case R.id.id_tab_weixin2:
                viewPager.setCurrentItem(1);
                iv2.setSelected(true);
                ivcur=iv2;
                llcur = ll2;
                break;
            case 2:
            case R.id.id_tab_weixin3:
                viewPager.setCurrentItem(2);
                iv3.setSelected(true);
                ivcur=iv3;
                llcur = ll3;
                break;
            case 3:
            case R.id.id_tab_weixin4:
                viewPager.setCurrentItem(3);
                iv4.setSelected(true);
                ivcur=iv4;
                llcur = ll4;

                //tiaozhuan();
                break;
        }
        llcur.setBackgroundColor(0xcf00ff00);
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}