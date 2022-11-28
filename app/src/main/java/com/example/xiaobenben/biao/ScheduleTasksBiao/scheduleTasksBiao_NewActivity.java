package com.example.xiaobenben.biao.ScheduleTasksBiao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.xiaobenben.R;

import java.util.Calendar;
import java.util.Locale;

public class scheduleTasksBiao_NewActivity extends AppCompatActivity{
    private Context context;
    private ScheduleTasksBiao scheduleTasksBiao;
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private scheduleTasksAdapter.scheduleNewClickListener listener;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_tasks_biao_new);
        context = this;
        scheduleTasksBiao = new ScheduleTasksBiao();

        Button bnt = findViewById(R.id.id_biao_scheduleTasks_new_add);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddItemDialog(context).show();
            }
        });


        lv = findViewById(R.id.id_biao_scheduleTasks_new_lv);
        lv.setAdapter(new scheduleTasksAdapter(context, scheduleTasksBiao, listener = new scheduleTasksAdapter.scheduleNewClickListener() {
            @Override
            public void callback_del(int i) {
                scheduleTasksBiao.deldeScheduleTasksInComItem(i);
                notify_biao();
            }
        }));



    }


    public void notify_biao(){
        lv.setAdapter(new scheduleTasksAdapter(context,scheduleTasksBiao,listener));
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
                tv.setText("：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
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
                        tv.setText(hourOfDay + "时" + minute  + "分");
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                ,true).show();
    }

}