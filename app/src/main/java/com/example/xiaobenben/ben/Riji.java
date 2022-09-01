package com.example.xiaobenben.ben;

import java.io.Serializable;

public class Riji implements Serializable {
    //一下内容全是瞎扯
    //string 限制长度  65535
    private String Content;
    private String NewTime;
    private String jianjie1;
    private String jianjie2;

    public void setContent(String content) {
        Content = content;
    }

    public void setNewTime(String newTime) {
        NewTime = newTime;
    }

    public Riji(){
        this.Content = "str";
        this.jianjie1 = "156165";
        this.jianjie2 = "1456a5";
    }

    public Riji(String nr, String jianjie1, String jianjie2) {
        this.Content = nr;
        this.jianjie1 = jianjie1;
        this.jianjie2 = jianjie2;
    }

    public Riji(String str){
        this.Content = str;
        this.jianjie1 = "156165";
        this.jianjie2 = "1456a5";
    }



    public void setJianjie1(String jianjie1) {
        this.jianjie1 = jianjie1;
    }

    public void setJianjie2(String jianjie2) {
        this.jianjie2 = jianjie2;
    }

    public String getContent() {
        return Content;
    }

    public String getNewTime() {
        return NewTime;
    }


    public String getJianjie1() {
        return jianjie1;
    }

    public String getJianjie2() {
        return jianjie2;
    }
}
