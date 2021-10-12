package com.example.vulcan.test01;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

//This activity is for stop the alarm when it rings
public class Stop_Alarm extends AppCompatActivity {


    private TextView hour_min;
    private String hour;
    private String minute;
    private MediaPlayer mediaPlayer  = new MediaPlayer() ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {//实现展示事件名称，时间，以及停止音乐播放
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_alarm);

        getIncomingContent_ItemName();

        String get_path = getIntent().getStringExtra("pathForStop");//根据key值来索引value值
        ////////////////////////////////////////////////////////////////////////////////////////////
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(get_path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////////////////////////////////////////////////




        final Button stopAlarm_button = findViewById(R.id.button_stop_alarm);

        stopAlarm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.release();
                Intent intent = new Intent(Stop_Alarm.this, Recycler_view.class);

                startActivity(intent);


            }
        });








    }


    public void getIncomingContent_ItemName(){

        String get_Item_Name = getIntent().getStringExtra("itemNameForStop");//根据key值来索引value值

        setIncomingContent_ItemName(get_Item_Name);
    }

    public void setIncomingContent_ItemName(String name){

        TextView prompt = (TextView)findViewById(R.id.text_stop_prompt);
        prompt.setText("It is time for: "+name+" wake up!!!");


    }





}
