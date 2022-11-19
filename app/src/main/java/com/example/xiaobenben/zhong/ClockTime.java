package com.example.xiaobenben.zhong;

import java.io.Serializable;
import java.util.List;

public class ClockTime implements Serializable {
    private String Hour;
    private String Min;
    private boolean isSelect;
    private static List<ClockTime> clockTimeList;
    public ClockTime(String Hour,String Min,boolean isSelect){
        this.Hour = Hour;
        this.Min = Min;
        this.isSelect = isSelect;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getMin() {
        return Min;
    }

    public void setMin(String min) {
        Min = min;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<ClockTime> getClockTimeList() {
        return clockTimeList;
    }

    public void setClockTimeList(List<ClockTime> clockTimeList) {
        this.clockTimeList = clockTimeList;
    }
}
