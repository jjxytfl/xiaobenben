package com.example.xiaobenben.ben;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaobenben.R;

public class ben_ChangeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView cur;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;

    private Ben ben;
    private int benid;

    private int selcolor;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben_change);

        ben = (Ben)this.getIntent().getSerializableExtra("ben");
        benid = this.getIntent().getIntExtra("benid",0);



        selcolor = 0x0000FF;
        color = 0x000000;

        TextView tv = findViewById(R.id.id_ben_new_tv);
        tv.setText(ben.getName());

        ImageButton delimgbnt = findViewById(R.id.id_ben_change_del_imgbnt);
        ImageButton pressedimgbnt = findViewById(R.id.id_ben_change_pressed_imgbnt);
        ImageButton sureimgbnt = findViewById(R.id.id_ben_change_sure_imgbnt);

        delimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment_ben.delBen(benid);
                onBackPressed();
            }
        });

        pressedimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        sureimgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Ben 构造新的ben
                Ben ben = new Ben(tv.getText().toString());
                BlankFragment_ben.changeBen(ben,benid);
                onBackPressed();
            }
        });



        ImageButton imgbnt = findViewById(R.id.id_ben_new_imgbnt);
        imgbnt.setVisibility(ImageButton.INVISIBLE);

        iv1 = findViewById(R.id.id_ben_new_imgbnt1);
        iv2 = findViewById(R.id.id_ben_new_imgbnt2);
        iv3 = findViewById(R.id.id_ben_new_imgbnt3);
        iv4 = findViewById(R.id.id_ben_new_imgbnt4);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);

        //判断cur为那个位置并且进行高亮处理
        cur = iv1;
        cur.setBackgroundColor(selcolor);


    }

    @Override
    public void onClick(View view) {
        cur.setBackgroundColor(color);

        if (iv1.equals(view)) {
            cur = iv1;
        } else if (iv2.equals(view)) {
            cur = iv2;
        } else if (iv3.equals(view)) {
            cur = iv3;
        } else if (iv4.equals(view)) {
            cur = iv4;
        }

        cur.setBackgroundColor(selcolor);
    }
}