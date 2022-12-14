package com.example.xiaobenben.zhong.Tomato;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaobenben.MainActivity;
import com.example.xiaobenben.R;
import com.example.xiaobenben.control.CircleImageView;
import com.example.xiaobenben.zhong.ClockActivity;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment_zhong extends Fragment {

    private TimeEntryAdapter mAdapter = null;

    public static Context context;



    public BlankFragment_zhong() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment_zhong newInstance(String param1, String param2) {
        BlankFragment_zhong fragment = new BlankFragment_zhong();
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_zhong, container, false);

        SpUtils.init(context);


        ListView lv = ((ListView)view.findViewById(R.id.listMain1));
        lv.setAdapter(mAdapter = new BlankFragment_zhong.TimeEntryAdapter(new ArrayList<TimeEntry>()));
        mAdapter.addAll(SpUtils.getCurrent());


        TextView tv = view.findViewById(R.id.id_zhong_huan);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, ClockActivity.class));
            }
        });


        CircleImageView button  = view.findViewById(R.id.id_zhong_tomato_add_but);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BlankFragment_zhong.AddItemDialog(context, new ICallback<TimeEntry>() {
                    @Override
                    public void onCallback(TimeEntry value) {
                        mAdapter.add(value);
                        SpUtils.saveCurrent(mAdapter.toList());
                    }
                }).show();
            }
        });


        CircleImageView button_go  = view.findViewById(R.id.id_zhong_tomato_go_but);
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAdapter.getCount()<1){
                    Toast.makeText(context, "???????????????????????????", Toast.LENGTH_SHORT).show();
                }
                new AlertDialog.Builder(context).setTitle("???????????????").setMessage("??????????????????????")
                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SpUtils.setStatus(true);
                                SpUtils.setStartTime(System.currentTimeMillis());
                                startActivity(new Intent(context,TomatoClockActivity.class));
                            }
                        }).setNegativeButton("??????",null).create().show();
            }
        });



        if(SpUtils.getStatus()){
            //startActivity(new Intent(context,TomatoClockActivity.class));
            Log.d("12345", "onCreate: ");
        }



        return view;
    }




    class TimeEntryAdapter extends ArrayAdapter<TimeEntry> {

        class TimeEntryViewHolder{
            TextView txtItemText;
            ImageButton btnDelete;
            LinearLayout itemBackground;
        }

        public TimeEntryAdapter(List<TimeEntry> objects) {
            super(context, R.layout.adapter_time_entry,R.id.txtItemText, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BlankFragment_zhong.TimeEntryAdapter.TimeEntryViewHolder holder = new BlankFragment_zhong.TimeEntryAdapter.TimeEntryViewHolder();
            View v = null;
            if(convertView == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                v = inflater.inflate(R.layout.adapter_time_entry,null);
                holder.itemBackground = v.findViewById(R.id.itemBackground);
                holder.txtItemText = v.findViewById(R.id.txtItemText);
                holder.btnDelete = v.findViewById(R.id.btnDeleteItem);
                v.setTag(holder);
            }
            else
            {
                v = (LinearLayout) convertView;
                holder = (BlankFragment_zhong.TimeEntryAdapter.TimeEntryViewHolder) v.getTag();
            }

            TextView rq_tv = v.findViewById(R.id.title1);
            TextView xq_tv = v.findViewById(R.id.title2);
            TextView tq_tv = v.findViewById(R.id.title3);
            TextView wd_tv = v.findViewById(R.id.title4);

            rq_tv.setText(MainActivity.biaoTime.getrq());
            xq_tv.setText(MainActivity.biaoTime.getxq());
            tq_tv.setText(MainActivity.biaoTime.gettq());
            wd_tv.setText(MainActivity.biaoTime.getwd());



            TimeEntry item = getItem(position);
            int minute = item.getDuration() / 60;
            holder.itemBackground.setBackgroundColor(item.getBackgroundColor());
            holder.txtItemText.setText(item.getName()+" "+minute+"??????");
            holder.btnDelete.setOnClickListener(new BlankFragment_zhong.TimeEntryAdapter.ItemDeleter(item));

            return v;
        }

        class ItemDeleter implements View.OnClickListener{
            TimeEntry target;

            public ItemDeleter(TimeEntry target) {
                this.target = target;
            }

            @Override
            public void onClick(View v) {
                BlankFragment_zhong.TimeEntryAdapter.this.remove(target);
                BlankFragment_zhong.TimeEntryAdapter.this.notifyDataSetChanged();
                SpUtils.saveCurrent(mAdapter.toList());
            }
        }

        public List<TimeEntry> toList(){
            ArrayList<TimeEntry> result = new ArrayList<>();
            for (int i = 0; i < getCount(); i++) {
                result.add(getItem(i));
            }
            return result;
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
                "??????","??????","??????","??????"
                //???????????????
        };

        private int _selectedColor = 0xFFE87A90;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_add);
            setTitle("???????????????");
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
                    txtDurationText.setText("?????????"+(value)+"??????");
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
                    ((TextView)v).setText("???");
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
                    BlankFragment_zhong.AddItemDialog.this.dismiss();
                }
            });
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(txtDescription.getText())){
                        Toast.makeText(context, "????????????????????????", Toast.LENGTH_SHORT).show();
                        txtDescription.requestFocus();
                        return;
                    }
                    callback.onCallback(new TimeEntry(txtDescription.getText().toString(),(numTime.getProgress()+5) * 60, _selectedColor));
                    BlankFragment_zhong.AddItemDialog.this.dismiss();
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