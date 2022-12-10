package com.example.xiaobenben.biao.TourTasksBiao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.BlankFragment_biao;
import com.example.xiaobenben.biao.ScheduleTasksBiao.ScheduleTasksBiao;
import com.example.xiaobenben.biao.ScheduleTasksBiao.scheduleTasksBiao_OperateActivity;
import com.example.xiaobenben.control.CircleImageView;

public class tourTasksBiao_OperateActivity extends AppCompatActivity {
    private TourTasksBiao tourTasksBiao;
    private Context context;
    private tourTasks_OperateAdapter.tourOperateClickListener listener;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_tasks_biao_operate);
        tourTasksBiao = new TourTasksBiao();
        context = this;


        int biaoid;
        {
            biaoid = getIntent().getIntExtra("biaoid",0);
            tourTasksBiao = (TourTasksBiao) getIntent().getSerializableExtra("biao");
        }

        lv = findViewById(R.id.id_biao_tourTasks_operate_lv);
        lv.setAdapter(new tourTasks_OperateAdapter(tourTasksBiao,context,listener = new tourTasks_OperateAdapter.tourOperateClickListener() {
            @Override
            public void callback_com(int i) {
                tourTasksBiao.comTourTasksItem(i);
                lv.setAdapter(new tourTasks_OperateAdapter(tourTasksBiao,context,listener));
            }

            @Override
            public void callback_mod(int i) {
                new ModItemDialog(context,i).show();
            }
        }));

        CircleImageView sure_bnt = findViewById(R.id.id_biao_tourTasks_operate_sure);
        sure_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment_biao.modify(biaoid,tourTasksBiao);
                onBackPressed();
            }
        });


        CircleImageView back_bnt = findViewById(R.id.id_biao_tourTasks_operate_back);
        back_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Button add_bnt = findViewById(R.id.id_biao_tourTasks_operate_add);
        add_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddItemDialog(context).show();
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
            setContentView(R.layout.dialog_tour_new);
            setTitle("添加行程");

            EditText places_et = findViewById(R.id.id_biao_tourTasks_new_dialog_places);
            EditText date_et = findViewById(R.id.id_biao_tourTasks_new_dialog_date);
            EditText prepare_et = findViewById(R.id.id_biao_tourTasks_new_dialog_prepare);
            EditText notes_et = findViewById(R.id.id_biao_tourTasks_new_dialog_notes);
            EditText expenses_et = findViewById(R.id.id_biao_tourTasks_new_dialog_expenses);
            EditText details_et = findViewById(R.id.id_biao_tourTasks_new_dialog_details);


            Button cancel_bnt = findViewById(R.id.id_biao_tourTasks_dialog_new_cancel);
            Button sure_bnt = findViewById(R.id.id_biao_tourTasks_dialog_new_sure);



            cancel_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddItemDialog.this.dismiss();
                }
            });

            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tourTasksBiao.addTourTasksComItem(
                            places_et.getText().toString(),
                            date_et.getText().toString(),
                            prepare_et.getText().toString(),
                            notes_et.getText().toString(),
                            expenses_et.getText().toString(),
                            details_et.getText().toString()
                    );
                    lv.setAdapter(new tourTasks_OperateAdapter(tourTasksBiao,context,listener));
                    AddItemDialog.this.dismiss();
                }
            });

        }
    }


    class ModItemDialog extends Dialog {
        int cur;
        public ModItemDialog(@NonNull Context context,int i) {
            super(context);
            this.cur = i;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_tour_new);
            setTitle("修改行程");

            EditText places_et = findViewById(R.id.id_biao_tourTasks_new_dialog_places);
            EditText date_et = findViewById(R.id.id_biao_tourTasks_new_dialog_date);
            EditText prepare_et = findViewById(R.id.id_biao_tourTasks_new_dialog_prepare);
            EditText notes_et = findViewById(R.id.id_biao_tourTasks_new_dialog_notes);
            EditText expenses_et = findViewById(R.id.id_biao_tourTasks_new_dialog_expenses);
            EditText details_et = findViewById(R.id.id_biao_tourTasks_new_dialog_details);


            Button del_bnt = findViewById(R.id.id_biao_tourTasks_dialog_new_cancel);
            Button sure_bnt = findViewById(R.id.id_biao_tourTasks_dialog_new_sure);

            del_bnt.setText("删除");





            places_et.setText(tourTasksBiao.getTourTasksInComItems().get(cur).getPlaces());
            date_et.setText(tourTasksBiao.getTourTasksInComItems().get(cur).getDate());
            prepare_et.setText(tourTasksBiao.getTourTasksInComItems().get(cur).getPrepare());
            notes_et.setText(tourTasksBiao.getTourTasksInComItems().get(cur).getNotes());
            expenses_et.setText(tourTasksBiao.getTourTasksInComItems().get(cur).getExpenses());
            details_et.setText(tourTasksBiao.getTourTasksInComItems().get(cur).getDetails());


            del_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tourTasksBiao.getTourTasksInComItems().remove(cur);
                    lv.setAdapter(new tourTasks_OperateAdapter(tourTasksBiao,context,listener));
                    ModItemDialog.this.dismiss();
                }
            });

            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tourTasksBiao.modTourTasksComItem(
                            cur,
                            places_et.getText().toString(),
                            date_et.getText().toString(),
                            prepare_et.getText().toString(),
                            notes_et.getText().toString(),
                            expenses_et.getText().toString(),
                            details_et.getText().toString()
                    );
                    lv.setAdapter(new tourTasks_OperateAdapter(tourTasksBiao,context,listener));
                    ModItemDialog.this.dismiss();
                }
            });

        }
    }

}