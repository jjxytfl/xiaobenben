package com.example.xiaobenben.biao;

import java.io.Serializable;

public class biaoTime implements Serializable {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public biaoTime(){

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
