package com.example.xiaobenben.biao.ScheduleTasksBiao;

import com.example.xiaobenben.biao.Biao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScheduleTasksBiao extends Biao {
    private List<ScheduleTasksComItem> scheduleTasksComItems;
    private List<ScheduleTasksInComItem> scheduleTasksInComItems;

    public ScheduleTasksBiao(){
        super();
        setBiaoType("日程计划表");
        scheduleTasksComItems = new ArrayList<>();
        scheduleTasksInComItems = new ArrayList<>();
    }

    public void deleteScheduleTasksInComItem(int i){
        scheduleTasksInComItems.remove(i);
    }

    public void modifyScheduleTasksInComItem(int i,String date,String time,String task){
        scheduleTasksInComItems.set(i,new ScheduleTasksInComItem(date, time, task));
    }


    public void addScheduleTasksInComItem(String date,String time,String task){
        scheduleTasksInComItems.add(new ScheduleTasksInComItem(date,time,task));
    }

    public void addScheduleTasksComItem(int i,String comDate,String comTime){
        ScheduleTasksInComItem temp = scheduleTasksInComItems.get(i);
        scheduleTasksComItems.add(new ScheduleTasksComItem(comDate,comTime, temp.date, temp.time,temp.task));
        scheduleTasksInComItems.remove(i);
    }

    public List<ScheduleTasksComItem> getScheduleTasksComItems() {
        return scheduleTasksComItems;
    }

    public List<ScheduleTasksInComItem> getScheduleTasksInComItems() {
        return scheduleTasksInComItems;
    }

    public void setScheduleTasksComItems(List<ScheduleTasksComItem> scheduleTasksComItems) {
        this.scheduleTasksComItems = scheduleTasksComItems;
    }

    public void setScheduleTasksInComItems(List<ScheduleTasksInComItem> scheduleTasksInComItems) {
        this.scheduleTasksInComItems = scheduleTasksInComItems;
    }

    static class ScheduleTasksComItem  implements Serializable{
        public String ComDate;
        public String ComTime;
        public String date;
        public String time;
        public String task;

        public String getDate(){
            return "2001-06-02";
        }

        public String getTask() {
            return task;
        }

        public ScheduleTasksComItem(String comDate, String comTime, String date, String time, String task) {
            ComDate = comDate;
            ComTime = comTime;
            this.date = date;
            this.time = time;
            this.task = task;
        }
    }


    static class ScheduleTasksInComItem  implements Serializable{
        public String date;
        public String time;
        public String task;

        public ScheduleTasksInComItem(String date, String time, String task) {
            this.date = date;
            this.time = time;
            this.task = task;
        }
    }

}
