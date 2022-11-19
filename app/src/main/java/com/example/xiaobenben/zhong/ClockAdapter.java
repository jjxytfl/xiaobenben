package com.example.xiaobenben.zhong;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.xiaobenben.R;

import java.util.List;

import cn.we.swipe.helper.WeSwipeHelper;
import cn.we.swipe.helper.WeSwipeProxyAdapter;

public class ClockAdapter extends WeSwipeProxyAdapter<ClockAdapter.ViewHolder> {
    private List<ClockTime> timeList;
    private Context context;

    public ClockAdapter(List<ClockTime> timeList,Context context){
        this.timeList = timeList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.clock_item,parent,false );
        return new ViewHolder( view );
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ClockTime clockTime = timeList.get( position );
        holder.Hour.setText( clockTime.getHour() );
        holder.Split.setText( ":" );
        holder.Min.setText( clockTime.getMin() );
        boolean isSelect = clockTime.isSelect();
        if (isSelect){
            holder.ClockSwitch.setChecked( true );
        }else {
            holder.ClockSwitch.setChecked( false );
        }
        holder.DeleteBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteItem(position);
            }
        } );
        holder.ClockSwitch.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    SelectModel(holder);
                }else {
                    NormalModel(holder);
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }
    private void DeleteItem(int position){
        timeList.remove( position );
        notifyDataSetChanged();
    }

    public void AddItem(ClockTime clockTime){
        timeList.add( clockTime );
        notifyDataSetChanged();
    }

    private void NormalModel(ViewHolder holder){
        holder.Hour.setTextColor( context.getResources().getColor( R.color.colorGrey ) );
        holder.Split.setTextColor( context.getResources().getColor( R.color.colorGrey ) );
        holder.Min.setTextColor( context.getResources().getColor( R.color.colorGrey ) );
    }

    private void SelectModel(ViewHolder holder){
        holder.Hour.setTextColor( context.getResources().getColor( R.color.colorWhite ) );
        holder.Split.setTextColor( context.getResources().getColor( R.color.colorWhite ) );
        holder.Min.setTextColor( context.getResources().getColor( R.color.colorWhite ) );
    }

    class ViewHolder extends RecyclerView.ViewHolder implements WeSwipeHelper.SwipeLayoutTypeCallBack {
        private TextView DeleteBtn,Hour,Split,Min;
        private LinearLayout Layout;
        private Switch ClockSwitch;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            DeleteBtn = itemView.findViewById( R.id.DeleteBtn );
            Hour = itemView.findViewById( R.id.Hour );
            Split = itemView.findViewById( R.id.Split );
            Min = itemView.findViewById( R.id.Min );
            Layout = itemView.findViewById( R.id.ItemLayout );
            ClockSwitch = itemView.findViewById( R.id.ClockSwitch );
        }

        @Override
        public float getSwipeWidth() {
            return DeleteBtn.getWidth();
        }

        /**
         * View that needs to slide in RecyclerView.
         *
         * @return View that needs to slide.
         */
        @Override
        public View needSwipeLayout() {
            return Layout;
        }

        /**
         * When an item is sliding and has not yet been restored,
         * you need to determine whether the current click event requires subview
         * processing or restoring the sliding state.Click events are handled only
         * by clicking on the Operations View
         *
         * @return null: Consumer click events and recovery animation,otherwise only recovery animation.
         */
        @Override
        public View onScreenView() {
            return Layout;
        }
    }
}
