package com.example.xiaobenben.biao.TourTasksBiao;

import com.example.xiaobenben.biao.Biao;
import com.example.xiaobenben.biao.biaoTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TourTasksBiao extends Biao {
    private List<TourTasksComItem> tourTasksComItems;
    private List<tourTasksInComItem> tourTasksInComItems;

    public TourTasksBiao(){
        super();
        tourTasksComItems = new ArrayList<>();
        tourTasksInComItems = new ArrayList<>();
        setBiaoType("旅游计划表");
    }





    static class TourTasksComItem  implements Serializable{
        private tourTasksInComItem inComItem;
        private biaoTime tiem;

        public TourTasksComItem(){

        }

        public TourTasksComItem(tourTasksInComItem inComItem, biaoTime tiem) {
            this.inComItem = inComItem;
            this.tiem = tiem;
        }
    }


    static class tourTasksInComItem  implements Serializable{
        private String places;
        private String date;
        private String prepare;
        private String notes;
        private String expenses;
        private String details;

        public  tourTasksInComItem(){

        }

        public tourTasksInComItem(String places, String date, String prepare, String notes, String expenses, String details) {
            this.places = places;
            this.date = date;
            this.prepare = prepare;
            this.notes = notes;
            this.expenses = expenses;
            this.details = details;
        }
    }
}
