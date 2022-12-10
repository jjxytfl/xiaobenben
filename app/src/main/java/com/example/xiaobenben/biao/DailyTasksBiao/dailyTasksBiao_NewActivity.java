package com.example.xiaobenben.biao.DailyTasksBiao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.xiaobenben.R;
import com.example.xiaobenben.biao.BlankFragment_biao;
import com.example.xiaobenben.biao.ScheduleTasksBiao.scheduleTasksBiao_NewActivity;
import com.example.xiaobenben.biao.TourTasksBiao.tourTasksAdapter;
import com.example.xiaobenben.biao.TourTasksBiao.tourTasksBiao_NewActivity;
import com.example.xiaobenben.control.CircleImageView;

import java.util.Calendar;
import java.util.Locale;

public class dailyTasksBiao_NewActivity extends AppCompatActivity {
    private DailyTasksBiao dailyTasksBiao;
    private Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private Context context;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_tasks_biao_new);
        context = this;
        dailyTasksBiao = new DailyTasksBiao();


        String biaoName = getIntent().getStringExtra("biaoName");
        dailyTasksBiao.setBiaoName(biaoName);



        lv = findViewById(R.id.id_biao_dailyTasks_new_lv);
        lv.setAdapter(new dailyTasksAdapter(this, dailyTasksBiao));


        CircleImageView sureImgbnt = findViewById(R.id.id_biao_dailyTasks_new_sure_imgbnt);
        sureImgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BlankFragment_biao.addBiao(dailyTasksBiao);
                finish();
            }
        });



        CircleImageView addImgbnt = findViewById(R.id.id_biao_dailyTasks_new_add_imgbnt);
        addImgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断最后一栏是否为空 ， 增加之后需要重新加载adapter
                //10-30 上述方法避免不了名字为空 应该在为空时给出提示 或强制添加 name
                new AddItemDialog(context).show();



            }
        });



        CircleImageView backImgbnt = findViewById(R.id.id_biao_dailyTasks_new_back_imgbnt);
        backImgbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




    }

    class AddItemDialog extends Dialog{
        public AddItemDialog(@NonNull Context context) {
            super(context);
        }


        final String[] predefines = {
                "学习","休息","活动","工作"
                //先弄这么多
        };

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_daily_new);
            setTitle("添加日程");

            EditText name_et = findViewById(R.id.id_biao_tourTasks_new_dialog_name);
            TextView time_tv = findViewById(R.id.id_biao_dailyTasks_new_dialog_time_tv);
            Button time_bnt = findViewById(R.id.id_biao_dailyTasks_new_dialog_time);
            Button yushe_bnt = findViewById(R.id.id_biao_dailyTasks_new_dialog_yushe);
            EditText detail_et = findViewById(R.id.id_biao_dailyTasks_new_dialog_details);

            time_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showTimePickerDialog((Activity) context,0,time_tv,calendar);
                }
            });

            yushe_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getContext()).setItems(predefines, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            name_et.setText(predefines[which]);
                        }
                    }).create().show();
                }
            });



            Button cancel_bnt = findViewById(R.id.id_biao_dailyTasks_dialog_new_cancel);
            Button sure_bnt = findViewById(R.id.id_biao_dailyTasks_dialog_new_sure);



            cancel_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AddItemDialog.this.dismiss();
                }
            });


            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dailyTasksBiao.addDailyTasksItem(name_et.getText().toString(),time_tv.getText().toString(),detail_et.getText().toString());
                    //lv.setAdapter(new dailyTasksAdapter(context, dailyTasksBiao));
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