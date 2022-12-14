package com.example.xiaobenben;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.xiaobenben.ben.Ben;
import com.example.xiaobenben.ben.BlankFragment_ben;
import com.example.xiaobenben.biao.BlankFragment_biao;
import com.example.xiaobenben.biao.biaoTime;
import com.example.xiaobenben.wo.BlankFragment_me;
import com.example.xiaobenben.zhong.Tomato.BlankFragment_zhong;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager2 viewPager;
    public static biaoTime biaoTime = new biaoTime();


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
        llcur.setBackgroundColor(0xdf00ff00);



        iv1.setSelected(true);
        ivcur=iv1;

    }

    /**
     * 根据当前日期获得是星期几
     * time=yyyy-MM-dd
     * @return
     */
    public static String getWeek() {
        biaoTime biaoTime = new biaoTime();
        String time = biaoTime.getDate();
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int wek=c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }
        return Week;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

//        Intent intent = new Intent();
//        /* 开启Pictures画面Type设定为image */
//        intent.setType("image/*");
//        /* 使用Intent.ACTION_GET_CONTENT这个Action */
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        /* 取得相片后返回本画面 */
//        startActivityForResult(intent, 1);
//        Uri uri = data.getData();
//        String img_url = uri.getPath();//这是本机的图片路径
//        ContentResolver cr = this.getContentResolver();
//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//            ImageView imageView = (ImageView) findViewById(R.id.write_competerelay_cover_iv);
//            /* 将Bitmap设定到ImageView */
//            imageView.setImageBitmap(bitmap);
//        } catch (FileNotFoundException e) {
//            Log.e("Exception", e.getMessage(),e);
//        }
        super.onActivityResult(requestCode, resultCode, data);
        // 这里没有判断是否匹配，data为空
       // Glide.with(this).load(data.getData()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(helpBinding.ivHelpImageFirst);
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
        llcur.setBackgroundColor(0xdf00ff00);
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}