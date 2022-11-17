package com.example.xiaobenben.ben;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.control.CircleImageView;

public class ben_NewActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView cur;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private int curSel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben_new);


        TextView tv = findViewById(R.id.id_ben_new_tv);

        CircleImageView imgbnt = findViewById(R.id.id_ben_new_imgbnt);

        iv1 = findViewById(R.id.id_ben_new_kuang1);
        iv2 = findViewById(R.id.id_ben_new_kuang2);
        iv3 = findViewById(R.id.id_ben_new_kuang3);
        iv4 = findViewById(R.id.id_ben_new_kuang4);

        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);

        iv1.setSelected(false);
        iv2.setSelected(false);
        iv3.setSelected(false);
        iv4.setSelected(false);

        cur = iv1;
        cur.setSelected(true);

        imgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tv.getText().toString();
                //构建 ben 类
                Ben ben = new Ben(name, String.valueOf(curSel));

                BlankFragment_ben.addBen(ben);
                finish();
            }
        });


    }

    @Override
    public void onClick(View view) {
        cur.setSelected(false);

        if (iv1.equals(view)) {
            cur = iv1;
            curSel = 1;
        } else if (iv2.equals(view)) {
            cur = iv2;
            curSel = 2;
        } else if (iv3.equals(view)) {
            cur = iv3;
            curSel = 3;
        } else if (iv4.equals(view)) {
            cur = iv4;
            curSel = 4;
        }

        cur.setSelected(true);
    }
}