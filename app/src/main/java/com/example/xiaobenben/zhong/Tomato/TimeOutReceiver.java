package com.example.xiaobenben.zhong.Tomato;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeOutReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(null==TomatoClockActivity.mInstance){
            Intent i = new Intent(context,TomatoClockActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("notification",true);
            context.startActivity(i);
        }
        else{
            TomatoClockActivity.mInstance.requireNotification();
        }
    }
}
