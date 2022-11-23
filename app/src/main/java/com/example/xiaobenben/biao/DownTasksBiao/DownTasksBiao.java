package com.example.xiaobenben.biao.DownTasksBiao;

import com.example.xiaobenben.biao.Biao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DownTasksBiao  extends Biao implements Serializable {
    private List<DownTasksItem> downTasksItemList;

    public DownTasksBiao(){
        super();
        downTasksItemList = new ArrayList<>();
    }

    public DownTasksBiao(List<DownTasksItem> downTasksItemList) {
        super();
        this.downTasksItemList = downTasksItemList;
    }


    public void addDownTasksItem(String name,int surplus){
        downTasksItemList.add(new DownTasksItem(name,surplus));
    }

    public List<DownTasksItem> getDownTasksItemList() {
        return downTasksItemList;
    }








    static class DownTasksItem implements Serializable{
        private String name;
        private int surplus;

        public DownTasksItem(String name, int surplus) {
            this.name = name;
            this.surplus = surplus;
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

        public void consume(){
            surplus--;
        }



    }
}
