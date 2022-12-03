package com.example.xiaobenben.biao.TourTasksBiao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.ScheduleTasksBiao.scheduleTasksBiao_NewActivity;

public class tourTasksBiao_NewActivity extends AppCompatActivity {
    private TourTasksBiao tourTasksBiao;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_tasks_biao_new);

        tourTasksBiao = new TourTasksBiao();
        context = this;


        ListView lv = findViewById(R.id.id_biao_tourTasks_new_lv);
        lv.setAdapter(new tourTasksAdapter(tourTasksBiao, context, new tourTasksAdapter.tourNewClickListener() {
            @Override
            public void callback_del(int i) {

            }

            @Override
            public void callback_mod(int i) {

            }
        }));


        Button add_bnt = findViewById(R.id.id_biao_tourTasks_new_add);
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
                    AddItemDialog.this.dismiss();
                }
            });

        }
    }
}