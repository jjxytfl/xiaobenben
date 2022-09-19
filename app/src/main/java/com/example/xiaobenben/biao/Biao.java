package com.example.xiaobenben.biao;

import java.io.Serializable;

public class Biao implements Serializable {
    private String biaoName;
    private String newTime;

    public Biao(){
        this.biaoName = "biaoName";
        this.newTime = "newTime";
    }

    public Biao(String biaoName, String newTime) {
        this.biaoName = biaoName;
        this.newTime = newTime;
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
