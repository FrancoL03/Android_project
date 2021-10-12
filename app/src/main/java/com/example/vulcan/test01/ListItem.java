package com.example.vulcan.test01;

public class ListItem {// This class is used for define the alarm item (which is shown in the recyclerView)   用于设置recyclerView里面的每个item的具体信息

    private String itemName;
    private String month;
    private String day;
    private String hours;
    private String minutes;
    private String musicName;





    public ListItem(String itemName, String month, String day, String hours, String minutes, String musicName) {
        this.itemName = itemName;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
        this.musicName = musicName;

    }

    public String getItemName() {
        return itemName;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHours() {
        return hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getMusicName() {
        return musicName;
    }


}
