package com.example.xiaobenben.biao;

import java.io.Serializable;

public class Biao implements Serializable {
    private String biaoName;
    private String biaoType;
    private biaoTime newTime;


    public Biao(){
        this.biaoType = "null";
        this.biaoName = "biaoName";
        this.newTime = new biaoTime();
    }

    public Biao(String biaoName, String newTime) {
        this.biaoType = "null";
        this.biaoName = biaoName;
        this.newTime = new biaoTime();
    }

    public Biao(String biaoName, String newTime, String biaoType) {
        this.biaoName = biaoName;
        this.newTime = new biaoTime();
        this.biaoType = biaoType;
    }

    public void setBiaoType(String biaoType) {
        this.biaoType = biaoType;
    }

    public String getBiaoType() {
        return biaoType;
    }

    public String getBiaoName() {
        return biaoName;
    }

    public biaoTime getNewTime() {
        return newTime;
    }

    public void setBiaoName(String biaoName) {
        this.biaoName = biaoName;
    }


    public float getCompletionRate(){
        //获取完成率

        return 0;
    }




}
