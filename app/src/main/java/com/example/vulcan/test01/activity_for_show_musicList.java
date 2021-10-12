package com.example.vulcan.test01;

import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class activity_for_show_musicList extends AppCompatActivity {

    private ListView mListView;
    private List<Song> list;
    private MyAdapter_Music adapter;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private String musicName;
    private String MusicPath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list_of_mine);

        initView();
//        final Button button_SaveMusic = findViewById(R.id.button_saveMusic);//用于shift to the new page
//        button_SaveMusic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {





        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      }//以上为OnCreate部分

    public void save_music (View view) {

        mediaPlayer.release();
        Log.d("Debug,putEtra Former",getIntent().getStringExtra("FormerActivityName01"));
        String FormerName = getIntent().getStringExtra("FormerActivityName01");

//        if(musicName==null){
//            Toast.makeText(this,"Please choose a song as the reminder!",Toast.LENGTH_LONG).show();
//        }
//        else {

            if (FormerName.equals("Create")) {

                String get_Item_Name = getIntent().getStringExtra("ItemName_forCreatePage");
                String get_Year = getIntent().getStringExtra("Year_forCreatePage");
                String get_Month = getIntent().getStringExtra("Month_forCreatePage");
                String get_Day = getIntent().getStringExtra("Day_forCreatePage");
                String get_Hours = getIntent().getStringExtra("Hours_forCreatePage");
                String get_Minutes = getIntent().getStringExtra("Minutes_forCreatePage");

                Intent intent = new Intent(activity_for_show_musicList.this, volley_json_parse_test.class);
                intent.putExtra("ItemName_forCreatePage", get_Item_Name);
                intent.putExtra("Year_forCreatePage", get_Year);
                intent.putExtra("Month_forCreatePage", get_Month);
                intent.putExtra("Day_forCreatePage", get_Day);
                intent.putExtra("Hours_forCreatePage", get_Hours);
                intent.putExtra("Minutes_forCreatePage", get_Minutes);
                intent.putExtra("songName_forCreatePage", musicName);
                intent.putExtra("MusicPath", MusicPath);

//                    String Music_Path = MusicPath;
//                    AlertReceiver alertReceiver = new AlertReceiver(Music_Path);

                Log.d("Debug,putEtra ItemName", get_Item_Name);
                Log.d("Debug,putEtra Month", get_Month);
                Log.d("Debug,putEtra Day", get_Day);
                Log.d("Debug,putEtra Hours", get_Hours);
                Log.d("Debug,putEtra Minutes", get_Minutes);
//                Log.d("Debug,putEtra MusicName", musicName);


                startActivity(intent);

            } else {

                String get_Item_Name = getIntent().getStringExtra("ItemName_forChangePage");
                String get_Month = getIntent().getStringExtra("Month_forChangePage");
                String get_Day = getIntent().getStringExtra("Day_forChangePage");
                String get_Hours = getIntent().getStringExtra("Hours_forChangePage");
                String get_Minutes = getIntent().getStringExtra("Minutes_forChangePage");

                Intent intent = new Intent(activity_for_show_musicList.this, Page_of_Change_Data.class);
                intent.putExtra("ItemName_forChangePage", get_Item_Name);
                intent.putExtra("Month_forChangePage", get_Month);
                intent.putExtra("Day_forChangePage", get_Day);
                intent.putExtra("Hours_forChangePage", get_Hours);
                intent.putExtra("Minutes_forChangePage", get_Minutes);
                intent.putExtra("songName_forChangePage", musicName);
                intent.putExtra("MusicPath", MusicPath);

//                    String Music_Path = MusicPath;
//                    AlertReceiver alertReceiver = new AlertReceiver(Music_Path);


                Log.d("Debug,putEtra ItemName", get_Item_Name);
                Log.d("Debug,putEtra Month", get_Month);
                Log.d("Debug,putEtra Day", get_Day);
                Log.d("Debug,putEtra Hours", get_Hours);
                Log.d("Debug,putEtra Minutes", get_Minutes);
//                Log.d("Debug,putEtra MusicName", musicName);


                startActivity(intent);
            }
//        }


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initView() {
        mListView = (ListView) findViewById(R.id.main_listview);
        list = new ArrayList<>();
        list = MusicUtils.getMusicData(this);
        adapter = new MyAdapter_Music(this,list);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //当用户点击音乐时，该方法被调用
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.d("Debug,putEtra Former",getIntent().getStringExtra("FormerActivityName01"));
                String FormerName = getIntent().getStringExtra("FormerActivityName01");
                if ( FormerName.equals("Create"))

                {

                    play(list.get(i).path);//先播放所点击的音乐 Play the certain music

                    MusicPath = list.get(i).path;
//                    String get_Item_Name = getIntent().getStringExtra("ItemName_forCreatePage");
//                    String get_Month = getIntent().getStringExtra("Month_forCreatePage");
//                    String get_Day = getIntent().getStringExtra("Day_forCreatePage");
//                    String get_Hours = getIntent().getStringExtra("Hours_forCreatePage");
//                    String get_Minutes = getIntent().getStringExtra("Minutes_forCreatePage");
//
//                    Intent intent = new Intent(activity_for_show_musicList.this, volley_json_parse_test.class);
//                    intent.putExtra("ItemName_forCreatePage",get_Item_Name);
//                    intent.putExtra("Month_forCreatePage",get_Month);
//                    intent.putExtra("Day_forCreatePage",get_Day);
//                    intent.putExtra("Hours_forCreatePage",get_Hours);
//                    intent.putExtra("Minutes_forCreatePage",get_Minutes);


                    musicName = list.get(i).song.toString();//用于截取所选音乐的名字 get the name of the music
//

//                    Log.d("Debug,putEtra ItemName",get_Item_Name);
//                    Log.d("Debug,putEtra Month",get_Month );
//                    Log.d("Debug,putEtra Day",get_Day);
//                    Log.d("Debug,putEtra Hours",get_Hours);
//                    Log.d("Debug,putEtra Minutes",get_Minutes );
//                    Log.d("Debug,putEtra MusicName",list.get(i).song);



                }

                else {


                    play(list.get(i).path);//先播放所点击的音乐 play the music
                    MusicPath = list.get(i).path;

//                    String get_Item_Name = getIntent().getStringExtra("ItemName_forChangePage");
//                    String get_Month = getIntent().getStringExtra("Month_forChangePage");
//                    String get_Day = getIntent().getStringExtra("Day_forChangePage");
//                    String get_Hours = getIntent().getStringExtra("Hours_forChangePage");
//                    String get_Minutes = getIntent().getStringExtra("Minutes_forChangePage");
//
//                    Intent intent = new Intent(activity_for_show_musicList.this, Page_of_Change_Data.class);
//                    intent.putExtra("ItemName_forChangePage",get_Item_Name);
//                    intent.putExtra("Month_forChangePage",get_Month);
//                    intent.putExtra("Day_forChangePage",get_Day);
//                    intent.putExtra("Hours_forChangePage",get_Hours);
//                    intent.putExtra("Minutes_forChangePage",get_Minutes);
//                    intent.putExtra("songName_forChangePage",list.get(i).song.toString());
                    musicName = list.get(i).song.toString();
//                    Log.d("Debug,putEtra ItemName",get_Item_Name);
//                    Log.d("Debug,putEtra Month",get_Month );
//                    Log.d("Debug,putEtra Day",get_Day);
//                    Log.d("Debug,putEtra Hours",get_Hours);
//                    Log.d("Debug,putEtra Minutes",get_Minutes );
//                    Log.d("Debug,putEtra MusicName",list.get(i).song);






                }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            }
        });






    }
    private void play(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
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
    }

    private void stopPlay(String path) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }












}
