package com.example.xiaobenben.biao.DownTasksBiao;

import com.example.xiaobenben.biao.Biao;
import com.example.xiaobenben.biao.biaoTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DownTasksBiao  extends Biao implements Serializable {
    private List<DownTasksItem> downTasksItemList;
    private List<HistoryDownTasksItem> historyDownTasksItemList;

    public DownTasksBiao(){
        super();
        setBiaoType("倒数计划表");
        downTasksItemList = new ArrayList<>();
        historyDownTasksItemList = new ArrayList<>();
    }

    public DownTasksBiao(List<DownTasksItem> downTasksItemList) {
        super();
        setBiaoType("倒数计划表");
        this.downTasksItemList = downTasksItemList;
        historyDownTasksItemList = new ArrayList<>();
    }


    public void addDownTasksItem(String name,int surplus){
        downTasksItemList.add(new DownTasksItem(name,surplus));
    }

    public void addHistoryDownTasksItem(String name,String tiem){
        historyDownTasksItemList.add(new HistoryDownTasksItem(new biaoTime(),name));
    }

    public void addDownTasksItem(){
        downTasksItemList.add(new DownTasksItem());
    }

    public void deleteDownTasksItem(int i){
        downTasksItemList.remove(i);
    }



    public List<DownTasksItem> getDownTasksItemList() {
        return downTasksItemList;
    }



    static class HistoryDownTasksItem implements Serializable{
        private biaoTime biaoTime;
        private String name;

        public HistoryDownTasksItem(com.example.xiaobenben.biao.biaoTime biaoTime, String name) {
            this.biaoTime = biaoTime;
            this.name = name;
        }


        public void setBiaoTime(com.example.xiaobenben.biao.biaoTime biaoTime) {
            this.biaoTime = biaoTime;
        }

        public void setName(String name) {
            this.name = name;
        }
    }








    static class DownTasksItem implements Serializable{
        private String name;
        private int surplus;

        public DownTasksItem(String name, int surplus) {
            this.name = name;
            this.surplus = surplus;
        }

        public DownTasksItem() {
            this.name = "name";
            this.surplus = 0;
        }

        public String getName() {
            return name;
        }

        public int getSurplus() {
            return surplus;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSurplus(int surplus) {
            this.surplus = surplus;
        }

        public void consume(String name,String time){
            surplus--;
        }



    }
}
