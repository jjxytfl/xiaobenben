package com.example.xiaobenben.biao.ScheduleTasksBiao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.BlankFragment_biao;
import com.example.xiaobenben.biao.DownTasksBiao.DownTasksBiao;

import java.util.Calendar;
import java.util.Locale;

public class scheduleTasksBiao_OperateActivity extends AppCompatActivity {
    private Context context;
    private ScheduleTasksBiao scheduleTasksBiao;
    private scheduleTasks_OperateAdapter.scheduleOperateClickListener listener;
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tasks_biao_operate);
        context = this;
        scheduleTasksBiao = new ScheduleTasksBiao();


        int biaoid;
        {
            biaoid = getIntent().getIntExtra("biaoid",0);
            scheduleTasksBiao = (ScheduleTasksBiao) getIntent().getSerializableExtra("biao");
        }


        lv = findViewById(R.id.id_biao_scheduleTasks_lv);
        lv.setAdapter(new scheduleTasks_OperateAdapter(context,scheduleTasksBiao,listener = new scheduleTasks_OperateAdapter.scheduleOperateClickListener() {
            @Override
            public void callback_consume(int i, boolean completion) {

            }

            @Override
            public void callback_Remarks(int i, String remark) {

            }
        }));

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new ModifyItemDialog(context,i).show();
                return true;
            }
        });


        Button add_bnt = findViewById(R.id.id_biao_scheduleTasks_operate_add);
        add_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddItemDialog(context).show();
            }
        });

        Button back_bnt = findViewById(R.id.id_biao_scheduleTasks_operate_back);
        back_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        Button sure_bnt = findViewById(R.id.id_biao_scheduleTasks_operate_sure);
        sure_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment_biao.modify(biaoid,scheduleTasksBiao);
                onBackPressed();
            }
        });



    }


    public void notify_biao(){
        lv.setAdapter(new scheduleTasks_OperateAdapter(context,scheduleTasksBiao,listener));
    }


    class AddItemDialog extends Dialog{
        public AddItemDialog(@NonNull Context context) {
            super(context);
        }


        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_schedule_new);
            setTitle("添加日程");

            EditText et = findViewById(R.id.id_biao_dialog_schedule_new_et);
            TextView date_tv = findViewById(R.id.id_biao_scheduleTasks_dialog_new_date);
            TextView time_tv = findViewById(R.id.id_biao_scheduleTasks_dialog_new_time);

            Button date_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_date_mod);
            Button time_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_time_mod);

            date_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog((Activity) context,0,date_tv,calendar);
                }
            });

            time_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTimePickerDialog((Activity) context,0,time_tv,calendar);
                }
            });



            Button cancel_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_cancel);
            Button sure_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_sure);

            cancel_bnt.setText("取消");


            cancel_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddItemDialog.this.dismiss();
                }
            });


            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scheduleTasksBiao.addScheduleTasksInComItem(date_tv.getText().toString(),time_tv.getText().toString(),et.getText().toString());
                    notify_biao();
                    AddItemDialog.this.dismiss();
                    //showDatePickerDialog((Activity) context,0,date_tv,calendar);
                    //showTimePickerDialog((Activity) context,0,time_tv,calendar);
                }
            });

        }
    }




    class ModifyItemDialog extends Dialog {
        private int cur;

        public ModifyItemDialog(@NonNull Context context,int i) {
            super(context);
            cur = i;
        }


        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_schedule_new);
            setTitle("添加日程");

            EditText et = findViewById(R.id.id_biao_dialog_schedule_new_et);
            TextView date_tv = findViewById(R.id.id_biao_scheduleTasks_dialog_new_date);
            TextView time_tv = findViewById(R.id.id_biao_scheduleTasks_dialog_new_time);

            et.setText(scheduleTasksBiao.getScheduleTasksInComItems().get(cur).task);
            date_tv.setText(scheduleTasksBiao.getScheduleTasksInComItems().get(cur).date);
            time_tv.setText(scheduleTasksBiao.getScheduleTasksInComItems().get(cur).time);

            Button date_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_date_mod);
            Button time_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_time_mod);

            date_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDatePickerDialog((Activity) context,0,date_tv,calendar);
                }
            });

            time_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTimePickerDialog((Activity) context,0,time_tv,calendar);
                }
            });



            Button cancel_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_cancel);
            Button sure_bnt = findViewById(R.id.id_biao_scheduleTasks_dialog_new_sure);

            cancel_bnt.setText("删除");


            cancel_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ModifyItemDialog.this.dismiss();
                }
            });


            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    scheduleTasksBiao.modifyScheduleTasksInComItem(cur,date_tv.getText().toString(),time_tv.getText().toString(),et.getText().toString());
                    notify_biao();
                    ModifyItemDialog.this.dismiss();
                    //showDatePickerDialog((Activity) context,0,date_tv,calendar);
                    //showTimePickerDialog((Activity) context,0,time_tv,calendar);
                }
            });

        }
    }



    /**
     * 日期选择
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 时间选择
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showTimePickerDialog(Activity activity,int themeResId, final TextView tv, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        new TimePickerDialog( activity,themeResId,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv.setText(hourOfDay + ":" + minute );
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                ,true).show();
    }
}