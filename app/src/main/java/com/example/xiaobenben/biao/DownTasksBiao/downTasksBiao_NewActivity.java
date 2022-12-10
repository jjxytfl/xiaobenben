package com.example.xiaobenben.biao.DownTasksBiao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.BlankFragment_biao;
import com.example.xiaobenben.biao.DailyTasksBiao.dailyTasksBiao_NewActivity;
import com.example.xiaobenben.control.CircleImageView;

public class downTasksBiao_NewActivity extends AppCompatActivity {

    private Context context;
    private DownTasksBiao downTasksBiao;
    private downTasksAdapter.downNewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_tasks_biao_new);

        downTasksBiao = new DownTasksBiao();
        context = this;


        String biaoName = getIntent().getStringExtra("biaoName");
        downTasksBiao.setBiaoName(biaoName);

        ListView lv = findViewById(R.id.id_biao_downTasks_new_lv);

        lv.setAdapter(new downTasksAdapter(context, downTasksBiao, listener = new downTasksAdapter.downNewClickListener() {
            @Override
            public void callback_modify(DownTasksBiao biao) {
                downTasksBiao = biao;
            }
        }));



        Button bnt = findViewById(R.id.id_biao_downTasks_new_add_bnt);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddItemDialog(context).show();
            }
        });


        CircleImageView sure_bnt = findViewById(R.id.id_biao_downTasks_new_sure_bnt);
        sure_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment_biao.addBiao(downTasksBiao);
                finish();
            }
        });


        CircleImageView back_bnt = findViewById(R.id.id_biao_downTasks_new_back_bnt);
        back_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




    }


    class AddItemDialog extends Dialog {
        public AddItemDialog(@NonNull Context context) {
            super(context);
        }


        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_down_new);
            setTitle("添加日程");

            EditText name_et = findViewById(R.id.id_biao_downTasks_new_dialog_name);
            EditText num_et = findViewById(R.id.id_biao_downTasks_new_dialog_num);
            EditText cycle_et = findViewById(R.id.id_biao_downTasks_new_dialog_cycle);






            Button cancel_bnt = findViewById(R.id.id_biao_downTasks_dialog_new_cancel);
            Button sure_bnt = findViewById(R.id.id_biao_downTasks_dialog_new_sure);



            cancel_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddItemDialog.this.dismiss();
                }
            });


            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    downTasksBiao.addDownTasksItem(name_et.getText().toString(),5);
                    //lv.setAdapter(new dailyTasksAdapter(context, dailyTasksBiao));
                    AddItemDialog.this.dismiss();
                    //showDatePickerDialog((Activity) context,0,date_tv,calendar);
                    //showTimePickerDialog((Activity) context,0,time_tv,calendar);
                }
            });

        }
    }
}