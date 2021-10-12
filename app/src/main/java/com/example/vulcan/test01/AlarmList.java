package com.example.vulcan.test01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AlarmList {  //This class is for creating a list to restore Alarms
//    private static ArrayList<Alarm> alarms = new ArrayList<Alarm>();
    private static HashMap<String,Integer> alarms = new HashMap<>();


    public static void addAlarm(String name, int quantity){
        alarms.put(name,quantity);
    }

    public static HashMap<String,Integer>  show_map(){
        return alarms;
    }

    public static int size(){
        return alarms.size();
    }

//    public static void removeAlarm(String itemName){
//        Iterator<Alarm> it = alarms.iterator();
//        while(it.hasNext()){
//            Alarm a = it.next();
//            if(a.getItemName().equals(itemName)){
//                it.remove();
//                a.setIfdeleted(true);
//            }
//        }
//    }

}
