package com.example.vulcan.test01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;


public class AlertReceiver extends BroadcastReceiver {
//    private static final String DB_URL = "jdbc:mysql://mysql.studev.groept.be:3306/a18_sd608"  ;                  //"jdbc:mysql://10.46.194.7/employee101";//设置数据库的url
//    private static final String USER = "a18_sd608";                                                                  //数据库名 (set the url for the database)
//    private static final String PASS = "a18_sd608";
    private String path;
    private String Item_name;
    private MediaPlayer mediaPlayer = new MediaPlayer();
//    protected String doInBackground(String... strings) {
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
//            if (conn ==null){}
//            else{
//                String ItemName_Receiver = getIntent()  ItemName_Receiver
//                String query = "SELECT Path from Items_List where ItemName = ";
//                Statement stmt = conn.createStatement();
//                stmt.executeUpdate(query);
//
//
//            }
//            conn.close();
//
//        }
//        catch (Exception e)
//        {
//
//            e.printStackTrace();
//        }
//
//        return msg;
//    }

    @Override
    public void onReceive(final Context context, Intent intent) {

//        String action = intent.getAction();
//        if (action.equals("com.fengchi.broadcastreceiver.MYRECEIVER")) {
//            // 接收到广播传来的数据 Receive the data from the receiver
//            path = intent.getStringExtra("Music_Path");
//            Log.d("Music_Path",path);
//        }
        path = intent.getStringExtra("Music_Path");
        Item_name = intent.getStringExtra("itemNameForReceiver");

        Log.d("Alarm name",Item_name);

        if (AlarmList.show_map().containsKey(Item_name)){  //This means the alarm is not deleted
            Intent intent_next = new Intent(context,Stop_Alarm.class);

            intent_next.putExtra("itemNameForStop",Item_name);
            intent_next.putExtra("pathForStop",path);

            intent_next.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent_next);

        }
        else{
            Log.d("Alarm map",AlarmList.show_map().toString());
            Log.d("Sorry, alarm deleted!!!",AlarmList.show_map().toString());
        }




        ////////////////////////////////////////////////////////////////////////////////////////////////////
//            mediaPlayer.reset();
//            mediaPlayer.setDataSource(path);
//            mediaPlayer.prepareAsync();
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mediaPlayer) {
////                    mediaPlayer.start();
//
//            Intent intent = new Intent(context,Stop_Alarm.class);
//
//            intent.putExtra("itemNameForStop",Item_name);
//            intent.putExtra("pathForStop",path);
//
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//
//
//
//                }
//            });

    }

        /*JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject abc = response.getJSONObject(i);
                                String path = abc.getString("MusicPath");
                                pathList.add(path);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );*/
    }


