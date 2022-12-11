package com.example.xiaobenben.wo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaobenben.R;
import com.example.xiaobenben.control.CircleImageView;
import com.example.xiaobenben.zhong.Tomato.AndroidUtils;
import com.example.xiaobenben.zhong.Tomato.ICallback;
import com.example.xiaobenben.zhong.Tomato.TimeEntry;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment_me#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment_me extends Fragment {

    public static Context context;


    View root;


    public BlankFragment_me() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment_me.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment_me newInstance(String param1, String param2) {
        BlankFragment_me fragment = new BlankFragment_me();
        Bundle args = new Bundle();



        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {


        }
    }

    private TextView own_tv;
    private TextView down_or_up_tv;
    private TextView personality_tv;
    private TextView feedback_tv;
    private TextView new_tv;
    private TextView gogo_tv;
    private TextView login_tv;
    private CircleImageView icon;


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("123456", "onActivityResult: " + requestCode + "res" + resultCode);
        if(requestCode == 15 && data != null){
            String sign = data.getStringExtra("sign");
            Log.d("123456", "onActivityResult: "  + sign);
            login_tv.setText("已登录用户名tfl");
            icon.setImageResource(R.drawable.tx);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(root == null){
            root = inflater.inflate(R.layout.fragment_blank_me, container, false);
        }

        icon = root.findViewById(R.id.id_wo_dl_icon);
        own_tv = root.findViewById(R.id.id_wo_own);
        down_or_up_tv = root.findViewById(R.id.id_wo_down_or_up);
        personality_tv = root.findViewById(R.id.id_wo_personality);
        feedback_tv = root.findViewById(R.id.id_wo_feedback);
        new_tv = root.findViewById(R.id.id_wo_new);
        gogo_tv = root.findViewById(R.id.id_wo_gogo);
        login_tv = root.findViewById(R.id.id_wo_dl);

        own_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ownDialog(context).show();
            }
        });

        feedback_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new feedbackDialog(context).show();
            }
        });

        new_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new newDialog(context).show();
            }
        });


        personality_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new personalityDialog(context).show();
            }
        });

        down_or_up_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new down_or_upDialog(context).show();
            }
        });


        login_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, LoginActivity.class );
                startActivityForResult(intent,15);
            }
        });





        return root;
    }

    class newDialog extends Dialog{

        public newDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_new);

            EditText viewById = findViewById(R.id.id_wo_new_et);
            Button can_bnt = findViewById(R.id.id_wo_new_can);
            Button sure_bnt = findViewById(R.id.id_wo_new_sure);



            can_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newDialog.this.dismiss();
                }
            });

            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newDialog.this.dismiss();
                }
            });


        }

    }


    class feedbackDialog extends Dialog{
        public feedbackDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_feedback);


            EditText viewById = findViewById(R.id.id_wo_feedback_et);
            Button can_bnt = findViewById(R.id.id_wo_feedback_can);
            Button sure_bnt = findViewById(R.id.id_wo_feedback_sure);



            can_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    feedbackDialog.this.dismiss();
                }
            });

            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    feedbackDialog.this.dismiss();
                }
            });

        }

    }


    class ownDialog extends Dialog{

        public ownDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_own);


            Button can_bnt = findViewById(R.id.id_wo_own_can);
            Button sure_bnt = findViewById(R.id.id_wo_own_sure);


            can_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ownDialog.this.dismiss();
                }
            });

            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ownDialog.this.dismiss();
                }
            });



        }
    }


    class personalityDialog extends Dialog{

        public personalityDialog(@NonNull Context context) {
            super(context);
        }


        private int _selectedColor = 0xFFE87A90;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_personality);

            colorPickerPanel = findViewById(R.id.id_wo_person_colorPickerPanel);
            Button can_bnt = findViewById(R.id.id_wo_person_can);
            Button sure_bnt = findViewById(R.id.id_wo_person_sure);


            can_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    personalityDialog.this.dismiss();
                }
            });

            sure_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    personalityDialog.this.dismiss();
                }
            });


            View.OnClickListener colorPicker = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _selectedColor = Integer.parseInt(v.getTag().toString());
                    for (TextView vi :
                            colorPickerViews) {
                        vi.setText(" ");
                    }
                    ((TextView)v).setText("√");
                }
            };

            int _4dp = AndroidUtils.dip2px(getContext(),4);


            for(int i=0;i<availableColors.length;i++){
                TextView pickerView = new TextView(getContext());
                pickerView.setBackgroundColor(availableColors[i]);
                colorPickerPanel.addView(pickerView);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pickerView.getLayoutParams();
                lp.width = AndroidUtils.dip2px(getContext(),28);
                lp.height = AndroidUtils.dip2px(getContext(),28);
                pickerView.setLayoutParams(lp);
                pickerView.setOnClickListener(colorPicker);
                pickerView.setTag(availableColors[i]);
                lp.setMargins(_4dp,_4dp,_4dp,_4dp);
                pickerView.setTextColor(Color.WHITE);
                pickerView.setGravity(Gravity.CENTER);
                pickerView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                colorPickerViews.add(pickerView);
            }

            colorPickerViews.get(0).performClick();

        }


        final int[] availableColors = {
                0xFFE87A90,
                0xFFF05E1C,
                0xFF43341B,
                0xFF90B44B,
                0xFF1B813E,
                0xFF33A6B8,
                0xFF005CAF,
                0xFF6A4C9C,
                0xFFC1328E,
                0xFF91989F,
                0xFF3A3226,
                0xFF434343,
                0xFF080808
        };

        ArrayList<TextView> colorPickerViews = new ArrayList<>();
        LinearLayout colorPickerPanel = null;
    }


    class down_or_upDialog extends Dialog{
        public down_or_upDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_down_or_up);

            Button can_bnt = findViewById(R.id.id_wo_down_or_up_can);
            Button down_bnt = findViewById(R.id.id_wo_down_or_up_down);
            Button up_bnt = findViewById(R.id.id_wo_down_or_up_up);

            can_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    down_or_upDialog.this.dismiss();
                }
            });

            down_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    down_or_upDialog.this.dismiss();
                }
            });

            up_bnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    down_or_upDialog.this.dismiss();
                }
            });
        }

    }


    class AddItemDialog extends Dialog {

        public AddItemDialog(Context context, ICallback<TimeEntry> callback) {
            super(context);
            this.callback = callback;
        }

        ICallback<TimeEntry> callback;

        final int[] availableColors = {
                0xFFE87A90,
                0xFFF05E1C,
                0xFF43341B,
                0xFF90B44B,
                0xFF1B813E,
                0xFF33A6B8,
                0xFF005CAF,
                0xFF6A4C9C,
                0xFFC1328E,
                0xFF91989F,
                0xFF3A3226,
                0xFF434343,
                0xFF080808
        };

        final String[] predefines = {
                "学习","休息","活动","工作"
                //先弄这么多
        };

        private int _selectedColor = 0xFFE87A90;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_add);
            setTitle("添加时间段");
            initUi();

            btnYushe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getContext()).setItems(predefines, new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            txtDescription.setText(predefines[which]);
                        }
                    }).create().show();
                }
            });
            numTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    valueChanged(seekBar.getProgress()+5);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    valueChanged(seekBar.getProgress()+5);

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    valueChanged(seekBar.getProgress()+5);

                }

                void valueChanged(int value){
                    txtDurationText.setText("时长："+(value)+"分钟");
                }
            });
            btnTimeMinus.setTag("-1");
            btnTimePlus.setTag("1");
            View.OnClickListener modifySeekbar = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int delta = Integer.parseInt(v.getTag().toString());
                    numTime.setProgress(numTime.getProgress()+delta);
                }
            };
            btnTimePlus.setOnClickListener(modifySeekbar);
            btnTimeMinus.setOnClickListener(modifySeekbar);

            View.OnClickListener colorPicker = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _selectedColor = Integer.parseInt(v.getTag().toString());
                    for (TextView vi :
                            colorPickerViews) {
                        vi.setText(" ");
                    }
                    ((TextView)v).setText("√");
                }
            };

            int _4dp = AndroidUtils.dip2px(getContext(),4);

            for(int i=0;i<availableColors.length;i++){
                TextView pickerView = new TextView(getContext());
                pickerView.setBackgroundColor(availableColors[i]);
                colorPickerPanel.addView(pickerView);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pickerView.getLayoutParams();
                lp.width = AndroidUtils.dip2px(getContext(),28);
                lp.height = AndroidUtils.dip2px(getContext(),28);
                pickerView.setLayoutParams(lp);
                pickerView.setOnClickListener(colorPicker);
                pickerView.setTag(availableColors[i]);
                lp.setMargins(_4dp,_4dp,_4dp,_4dp);
                pickerView.setTextColor(Color.WHITE);
                pickerView.setGravity(Gravity.CENTER);
                pickerView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                colorPickerViews.add(pickerView);
            }

            colorPickerViews.get(0).performClick();

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddItemDialog.this.dismiss();
                }
            });
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(txtDescription.getText())){
                        Toast.makeText(context, "请输入时间段说明", Toast.LENGTH_SHORT).show();
                        txtDescription.requestFocus();
                        return;
                    }
                    callback.onCallback(new TimeEntry(txtDescription.getText().toString(),(numTime.getProgress()+5) * 60, _selectedColor));
                    AddItemDialog.this.dismiss();
                }
            });
        }

        ArrayList<TextView> colorPickerViews = new ArrayList<>();

        Button btnConfirm = null;
        Button btnCancel = null;
        LinearLayout colorPickerPanel = null;
        Button btnTimePlus = null;
        SeekBar numTime = null;
        Button btnTimeMinus = null;
        TextView txtDurationText = null;
        Button btnYushe = null;
        EditText txtDescription = null;

        //ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh

        void initUi(){
            btnConfirm = (Button)findViewById(R.id.btnConfirm);
            btnCancel = (Button)findViewById(R.id.btnCancel);
            colorPickerPanel = (LinearLayout)findViewById(R.id.colorPickerPanel);
            btnTimePlus = (Button)findViewById(R.id.btnTimePlus);
            numTime = (SeekBar)findViewById(R.id.numTime);
            btnTimeMinus = (Button)findViewById(R.id.btnTimeMinus);
            txtDurationText = (TextView)findViewById(R.id.txtDurationText);
            btnYushe = (Button)findViewById(R.id.btnYushe);
            txtDescription = (EditText)findViewById(R.id.txtDescription);
        }




    }

}