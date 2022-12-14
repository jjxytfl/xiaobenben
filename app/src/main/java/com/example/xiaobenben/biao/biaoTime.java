package com.example.xiaobenben.biao;

import android.icu.util.Calendar;
import android.text.format.Time;

import java.io.Serializable;

public class biaoTime implements Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public biaoTime(){
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        int year = calendar.get(Calendar.YEAR);
//月
        int month = calendar.get(Calendar.MONTH)+1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
//获取系统时间
//小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//分钟
        int minute = calendar.get(Calendar.MINUTE);
//秒
        int second = calendar.get(Calendar.SECOND);

        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;

    }

    public biaoTime(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public biaoTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }


    public biaoTime(int y, int m, int d){
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public String gettq(){
        return "晴天";
    }
    public String getwd(){
        return "6℃";
    }

    public String getxq(){
        return "星期三";
    }
    public String getrq() {
        return "2022年12月14日";
    }


    public String getDate(){
        return ""+year+"-"+month+"-"+day;
    }

    public String getTime(){
        return ""+hour;
    }


    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }


}
