package com.example.xiaobenben.biao.DailyTasksBiao;

import com.example.xiaobenben.biao.Biao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DailyTasksBiao extends Biao implements Serializable {
    public List<DailyTasksItem> dailyTasksItemList;



    static class DailyTasksItem implements Serializable{
        public DailyTasksItem(String name, String time, String completion, String details) {
            this.name = name;
            this.time = time;
            this.completion = completion;
            this.details = details;
        }

        public void setCompletion(String completion) {
            this.completion = completion;
        }

        private String name;
        private String time;
        private String completion;
        private String details;

        public String getName() {
            return name;
        }

        public String getTime() {
            return time;
        }

        public String getDetails() {
            return details;
        }


        public void setName(String name) {
            this.name = name;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setDetails(String details) {
            this.details = details;
        }
    }



    public DailyTasksBiao(){
        super();
        dailyTasksItemList = new ArrayList<>();
        dailyTasksItemList.add(new DailyTasksItem("Name","Time","no","Details"));
    }


    public void addDailyTasksItem(String n,String t,String d){
        dailyTasksItemList.add(new DailyTasksItem(n,t,"no",d));
    }

    public List<DailyTasksItem> getDailyTasksItemList() {
        return dailyTasksItemList;
    }
}
