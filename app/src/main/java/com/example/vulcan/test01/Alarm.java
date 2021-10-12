package com.example.vulcan.test01;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class Alarm extends AppCompatActivity{  //This class is for restore alarm items

    private Calendar calendar = Calendar.getInstance();
    private String path;
    private String itemName;
    private boolean ifdeleted;
    private AlarmManager alarmManager;
    private AlertReceiver myReceiver;

    public Alarm(){}

    public Alarm(Calendar calendar, String path, String itemName) {
        this.calendar = calendar;
        this.path = path;
        this.itemName = itemName;
        ifdeleted = false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wait_for_alarm);
        startAlarm();

        Button btnn = (Button)findViewById(R.id.button_toRecycler);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Alarm.this, Recycler_view.class);
                startActivity(intent);


            }
        });

    }



    public void startAlarm(){
        myReceiver = new AlertReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.fengchi.broadcastreceiver.MYRECEIVER");
        registerReceiver(myReceiver, intentFilter);

        String path01 = getIntent().getStringExtra("pathForAlarm");


        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);//这句话不能放在onCreate里面！！！
        Intent intent = new Intent(Alarm.this, AlertReceiver.class);


//        intent.putExtra("pathForAlarm", path01);         //向广播接收器传递数据
        intent.putExtra("Music_Path",path01);//向Receiver中传递path // Deliver the path to the Receiver



        String itemName01 = getIntent().getStringExtra("itemNameForAlarmFromCreate");
        String itemName02 = getIntent().getStringExtra("itemNameForAlarmFromChange");

        String alarm_map_name;
        if(itemName01 == null){
            intent.putExtra("itemNameForReceiver",itemName02);//向Receiver中传递name // Deliver the name to the Receiver
            alarm_map_name = itemName02;
        }
        else{
            intent.putExtra("itemNameForReceiver",itemName01);//向Receiver中传递name //Deliver the name to the Receiver
            alarm_map_name = itemName01;
        }

        String hour_calendar = getIntent().getStringExtra("hourForAlarm");
        String minute_calendar = getIntent().getStringExtra("minuteForAlarm");
        String year_calendar = getIntent().getStringExtra("yearForAlarm");
        String month_calendar = getIntent().getStringExtra("monthForAlarm");
        String day_calendar = getIntent().getStringExtra("dayForAlarm");


        int hour_calendar_int = Integer.parseInt(hour_calendar);
        int minute_calendar_int = Integer.parseInt(minute_calendar);
//        int year_calendar_int = Integer.parseInt(year_calendar);
        int month_calendar_int = Integer.parseInt(month_calendar);
        int day_calendar_int = Integer.parseInt(day_calendar);

//        calendar.set(Calendar.YEAR,year_calendar_int);
        calendar.set(Calendar.MONTH,month_calendar_int);
        calendar.set(Calendar.DAY_OF_MONTH,day_calendar_int);
        calendar.set(Calendar.HOUR_OF_DAY,hour_calendar_int);
        calendar.set(Calendar.MINUTE,minute_calendar_int);
        calendar.set(Calendar.SECOND,0);

        int id = AlarmList.show_map().get(alarm_map_name) ;

        Log.d("Alarm id",String.valueOf(id));
        Log.d("Alarm map",AlarmList.show_map().toString());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(Alarm.this,id,intent,PendingIntent.FLAG_UPDATE_CURRENT);


        if(calendar.before((Calendar.getInstance()))){
            calendar.add(Calendar.DATE,1);
        }




        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
    }



    public String getItemName(){
        return itemName;
    }

    public void setIfdeleted(boolean ifdeleted) {
        this.ifdeleted = ifdeleted;
    }

    public boolean getIfdeleted() {
        return ifdeleted;
    }

//    public boolean notDeleted(){
//        return false;
//    }
//
//    public boolean tureDeleted(){
//        return true;
//    }


}
