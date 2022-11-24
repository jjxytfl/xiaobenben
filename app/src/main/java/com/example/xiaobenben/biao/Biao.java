package com.example.xiaobenben.biao;

import java.io.Serializable;

public class Biao implements Serializable {
    private String biaoName;
    private String newTime;
    private String biaoType;

    public Biao(){
        this.biaoType = "null";
        this.biaoName = "biaoName";
        this.newTime = "newTime";
    }

    public Biao(String biaoName, String newTime) {
        this.biaoType = "null";
        this.biaoName = biaoName;
        this.newTime = newTime;
    }

    public Biao(String biaoName, String newTime, String biaoType) {
        this.biaoName = biaoName;
        this.newTime = newTime;
        this.biaoType = biaoType;
    }

    public String getBiaoType() {
        return biaoType;
    }

    public String getBiaoName() {
        return biaoName;
    }

    public String getNewTime() {
        return newTime;
    }

    public void setBiaoName(String biaoName) {
        this.biaoName = biaoName;
    }

    public void setNewTime(String newTime) {
        this.newTime = newTime;
    }

    public float getCompletionRate(){
        //获取完成率

        return 0;
    }




}
