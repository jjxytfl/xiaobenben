package com.example.xiaobenben.KillProcess;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class KillProcess {
    private static KillProcess killProcess = null;
    private List<Activity> activityList = new ArrayList<>(  );
    private KillProcess(){

    }
    public static  KillProcess getInstance(){
        if (killProcess == null){
            Sync();
        }
        return killProcess;
    }
    private static synchronized void Sync(){
        if (killProcess == null){
            killProcess = new KillProcess();
        }
    }
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }
    public void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
