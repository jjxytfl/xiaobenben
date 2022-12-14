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

    public List<TourTasksComItem> getTourTasksComItems() {
        return tourTasksComItems;
    }

    public List<tourTasksInComItem> getTourTasksInComItems() {
        return tourTasksInComItems;
    }

    public void comTourTasksItem(int i){
        tourTasksInComItem item = tourTasksInComItems.remove(i);
        tourTasksComItems.add(new TourTasksComItem(item,new biaoTime()));
    }


    public void addTourTasksComItem(String places, String date, String prepare, String notes, String expenses, String details){
        tourTasksInComItems.add(new tourTasksInComItem(places,date,prepare,notes,expenses,details));
    }

    public void modTourTasksComItem(int i,String places, String date, String prepare, String notes, String expenses, String details){
        tourTasksInComItems.set(i,new tourTasksInComItem(places,date,prepare,notes,expenses,details));
    }


    static class TourTasksComItem  implements Serializable{
        private tourTasksInComItem inComItem;
        private biaoTime time;

        public TourTasksComItem(){

        }

        public TourTasksComItem(tourTasksInComItem inComItem, biaoTime time) {
            this.inComItem = inComItem;
            this.time = time;
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

        public String getPlaces() {
            return places;
        }

        public String getDate() {
            return date;
        }

        public String getPrepare() {
            return prepare;
        }

        public String getNotes() {
            return notes;
        }

        public String getExpenses() {
            return expenses;
        }

        public String getDetails() {
            return details;
        }
    }
}
