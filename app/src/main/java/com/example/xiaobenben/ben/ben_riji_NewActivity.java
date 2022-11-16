package com.example.xiaobenben.ben;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.control.CircleImageView;

public class ben_riji_NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben_riji_new);

        TextView numTv = findViewById(R.id.id_ben_riji_num_tv);


        EditText et = findViewById(R.id.id_ben_riji_new_et);

        et.setFocusable(true);
        et.setFocusableInTouchMode(true);

        et.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点
                    et.setCursorVisible(true);//显示光标

                } else {
                    // 失去焦点
                }
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                numTv.setText("字数：" + editable.toString().length());
            }
        });





        CircleImageView imgbnt1 = findViewById(R.id.id_ben_riji_new_imgbnt1);
        CircleImageView imgbnt2 = findViewById(R.id.id_ben_riji_new_imgbnt2);

        int i = this.getIntent().getIntExtra("id", 0);

        imgbnt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出前处理

                finish();

            }
        });


        imgbnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存
                String nr = et.getText().toString();
                BlankFragment_ben.addRiji(i,new Riji(nr));

                finish();
            }
        });

    }
}