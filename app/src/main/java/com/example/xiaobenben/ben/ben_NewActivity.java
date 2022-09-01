package com.example.xiaobenben.ben;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaobenben.R;

public class ben_NewActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView cur;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;

    private int selcolor;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben_new);

        selcolor = 0x0000FF;
        color = 0x000000;

        TextView tv = findViewById(R.id.id_ben_new_tv);

        ImageButton imgbnt = findViewById(R.id.id_ben_new_imgbnt);

        iv1 = findViewById(R.id.id_ben_new_imgbnt1);
        iv2= findViewById(R.id.id_ben_new_imgbnt2);
        iv3 = findViewById(R.id.id_ben_new_imgbnt3);
        iv4 = findViewById(R.id.id_ben_new_imgbnt4);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);

        cur = iv1;
        cur.setBackgroundColor(selcolor);


        imgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tv.getText().toString();
                //构建 ben 类
                Ben ben = new Ben(name);

                BlankFragment_ben.addBen(ben);
                finish();
            }
        });


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