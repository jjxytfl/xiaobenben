package com.example.xiaobenben.biao.DailyTasksBiao;

import java.util.List;
import java.util.Map;

public class DailyTasksBiaoHistory {
    private Map<String,DailyTasksHistoryItem> stringDailyTasksHistoryItemMap;

    private float CompletionRate;
    private String date;

    final class DailyTasksHistoryItem{
        private String name;
        private String time;
        private String details;
        private String Completion;
    }

}
