package com.example.xiaobenben.ben;

import com.example.xiaobenben.biao.biaoTime;

import java.io.Serializable;

public class Riji implements Serializable {
    //一下内容全是瞎扯
    //string 限制长度  65535
    private String Content;
    private biaoTime NewTime;
    private String jianjie1;
    private String jianjie2;

    public void setContent(String content) {
        Content = content;
    }

    public void setNewTime(String newTime) {
        NewTime = new biaoTime();
    }

    public Riji(){
        this.Content = "str";
        this.jianjie1 = "156165";
        this.jianjie2 = "1456a5";
        NewTime = new biaoTime();
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
        NewTime = new biaoTime();
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
        return NewTime.getDate();
    }


    public String getJianjie1() {
        if(getContent().length()<=10){
            return getContent();
        }
        return getContent().substring(0,10);
    }

    public String getJianjie2() {
        if(getContent().length()<=10){
            return "";
        }

        if(getContent().length() <= 20){
            return getContent().substring(10);
        }

        return getContent().substring(10,20);
    }
}
