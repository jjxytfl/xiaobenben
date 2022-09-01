package com.example.xiaobenben.ben;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
//Serializable作用是 可以通过intent接受
public class Ben implements Serializable {
    private String Name;
    private int count;
    private String ImgUrl;

    //
    private Time NewTime;
    private List<Riji> rijiList;

    public Ben(){
        Name = "name";
        //moren
        ImgUrl = "默认img";
        count = 0;
        rijiList = new ArrayList<>();
    }

    public Ben(String name) {
        Name = name;
        //moren
        ImgUrl = "默认img";
        count = 0;
        rijiList = new ArrayList<>();
    }


    public String getName() {
        return Name;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public Time getNewTime() {
        return NewTime;
    }

    public List<Riji> getRijiList() {
        return rijiList;
    }

    public int getCount() {
        return count;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public void setNewTime(String newTime) {
        //
        //NewTime = newTime;
    }

    public void setRijiList(List<Riji> rijiList) {
        this.rijiList = rijiList;
    }
}
