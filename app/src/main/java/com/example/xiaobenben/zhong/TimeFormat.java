package com.example.xiaobenben.zhong;


public class TimeFormat {
    private static TimeFormat timeFormat;
    private TimeFormat(){

    }
    public static TimeFormat getInstance(){
        if (timeFormat == null){
            sync();
        }
        return timeFormat;
    }
    private static synchronized void sync(){
        if (timeFormat == null){
            timeFormat = new TimeFormat();
        }
    }
    public String HandleHour(int hour){
        if (hour < 10){
            return "0"+hour;
        }else {
            return String.valueOf( hour );
        }
    }
    public String HandleWeek(int week){
        String weekday = "";
        switch (week){
            case 1:
                weekday = "星期一";
                break;
            case 2:
                weekday = "星期二";
                break;
            case 3:
                weekday = "星期三";
                break;
            case 4:
                weekday = "星期四";
                break;
            case 5:
                weekday = "星期五";
                break;
            case 6:
                weekday = "星期六";
                break;
            case 7:
                weekday = "星期七";
                break;
        }
        return weekday;
    }
}
