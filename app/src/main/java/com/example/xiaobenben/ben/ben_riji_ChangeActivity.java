package com.example.xiaobenben.ben;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.xiaobenben.R;

public class ben_riji_ChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ben_riji_change);


        EditText et = findViewById(R.id.id_ben_riji_new_et);

        ImageButton imgbnt1 = findViewById(R.id.id_ben_riji_new_imgbnt1);
        ImageButton imgbnt2 = findViewById(R.id.id_ben_riji_new_imgbnt2);

        int i = this.getIntent().getIntExtra("i", 0);
        int benid = this.getIntent().getIntExtra("benid", 0);
        Riji rj = (Riji) this.getIntent().getSerializableExtra("riji");


        et.setText(rj.getContent());


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
                BlankFragment_ben.changeRiji(benid,i,new Riji(nr));

                finish();
            }
        });


    }
}