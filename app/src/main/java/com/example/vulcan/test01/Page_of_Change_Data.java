package com.example.vulcan.test01;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;

//This activity is used for changing the item data
public class Page_of_Change_Data extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final String DB_URL = "jdbc:mysql://mysql.studev.groept.be:3306/a18_sd608"  ;                  //"jdbc:mysql://10.46.194.7/employee101";//设置数据库的url
    private static final String USER = "a18_sd608";                                                                  //数据库名
    private static final String PASS = "a18_sd608";
    private Calendar c = Calendar.getInstance();

    private int yearOfCalendar;
    private int monthOfCalendar;
    private int dayOfCalendar;

    private String year_string ;
    private String month_string ;
    private String day_string ;
    private int hourOfCalendar;
    private int minuteOfCalendar;

    private TextView Edit_Text_Insert_ItemName_ChangePage;
    private TextView Edit_Text_Month_ChangePage;
    private TextView Edit_Text_Days_ChangePage;
    private TextView Edit_Text_Hours_ChangePage;
    private TextView Edit_Text_Minutes_ChangePage;
    private EditText Edit_Text_MusicName_ChangePage;



    private RequestQueue mQueue;
    private TextView Mysql_result02;
    private boolean ifChooseHourAndMinute = false;

    private String MusicName_s;
    private String itemName_s;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_of_change_data);



        Button alarm_Button = (Button) findViewById(R.id.timeButton);
        alarm_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        Button date_Button = (Button) findViewById(R.id.button_date_change);
        date_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        getIncomingContent_ForAll();
        if (getIntent().getStringExtra("ItemName_forChangePage") != null) {

            getIncomingContent_ForAll_02();

        }

        mQueue = Volley.newRequestQueue(this);//请求队列对象
        Mysql_result02 = (TextView) findViewById(R.id.textView_Mysql_Result02);

//
//        final Button button_SaveData = findViewById(R.id.button_SaveData);//用于shift to the new page
//        button_SaveData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

        Edit_Text_Insert_ItemName_ChangePage = findViewById(R.id.editText_itemName_ChangePage);
        Edit_Text_Month_ChangePage = findViewById(R.id.editText_month_ChangePage);
        Edit_Text_Days_ChangePage =findViewById(R.id.editText_day_ChangePage);
        Edit_Text_Hours_ChangePage = findViewById(R.id.editText_hours_ChangePage);
        Edit_Text_Minutes_ChangePage = findViewById(R.id.editText_minutes_ChangePage);
        Edit_Text_MusicName_ChangePage =findViewById(R.id.editText_music_ChangePage);





    }

    public void save_click(View view){

        boolean check = true;


        MusicName_s = Edit_Text_MusicName_ChangePage.getText().toString();
        if (MusicName_s.equals("")) { //This is going to make sure you have to reset the music (every time you want to change the alarm item)
            check = false;
        }

        if (check == true) {

            int month_ = Integer.parseInt(Edit_Text_Month_ChangePage.getText().toString());
            int month_minus_1 = month_ - 1;
            String month_string = String.valueOf(month_minus_1);

            Intent intent_Alarm_Change = new Intent(Page_of_Change_Data.this, Alarm.class);
            intent_Alarm_Change.putExtra("pathForAlarm", getIntent().getStringExtra("MusicPath"));
            intent_Alarm_Change.putExtra("itemNameForAlarmFromChange", Edit_Text_Insert_ItemName_ChangePage.getText().toString());
//                intent_Alarm_Change.putExtra("yearForAlarm",year_string);
            intent_Alarm_Change.putExtra("monthForAlarm", month_string);
            intent_Alarm_Change.putExtra("dayForAlarm", Edit_Text_Days_ChangePage.getText().toString());
            intent_Alarm_Change.putExtra("hourForAlarm", Edit_Text_Hours_ChangePage.getText().toString());
            intent_Alarm_Change.putExtra("minuteForAlarm", Edit_Text_Minutes_ChangePage.getText().toString());

            Alarm alarm = new Alarm(c, getIntent().getStringExtra("MusicPath"), Edit_Text_Insert_ItemName_ChangePage.getText().toString());
//            AlarmList.addAlarm(alarm);

            Send objSend = new Send();
            objSend.execute("");
            Log.d("Alarm map",AlarmList.show_map().toString());
            startActivity(intent_Alarm_Change);


        } else {
            Toast.makeText(this, "Please fill in all the data and choose your music!", Toast.LENGTH_LONG).show();
        }
    }




    public void getIncomingContent_ForAll(){

      String get_Item_Name = getIntent().getStringExtra("ItemName");
      String get_Month = getIntent().getStringExtra("Month");
      String get_Day = getIntent().getStringExtra("Day");
      String get_Hours = getIntent().getStringExtra("Hours");
      String get_Minutes = getIntent().getStringExtra("Minutes");
      String get_Music = getIntent().getStringExtra("Music");

//      AlarmList.removeAlarm(get_Item_Name);
      setIncomingContent_ItemName(get_Item_Name,get_Month,get_Day,get_Hours,get_Minutes,get_Music);

    }


    public void setIncomingContent_ItemName(String ItemName,String Month,String Day, String Hours, String Minutes, String Music){

        TextView itemName_ = (TextView) findViewById(R.id.editText_itemName_ChangePage);
        TextView month_ = (TextView) findViewById(R.id.editText_month_ChangePage);
        TextView day_ = (TextView) findViewById(R.id.editText_day_ChangePage);
        TextView hours_ = (TextView) findViewById(R.id.editText_hours_ChangePage);
        TextView minutes_ = (TextView) findViewById(R.id.editText_minutes_ChangePage);
        EditText music_ = (EditText) findViewById(R.id.editText_music_ChangePage);


        itemName_.setText(ItemName);
        month_.setText(Month);
        day_.setText(Day);
        hours_.setText(Hours);
        minutes_.setText(Minutes);
        music_.setText("");




    }


    public void getIncomingContent_ForAll_02(){//For changes happen again

        String get_Item_Name = getIntent().getStringExtra("ItemName_forChangePage");//根据key值来索引value值
        year_string = getIntent().getStringExtra("Year_forChangePage");
        String get_Month = getIntent().getStringExtra("Month_forChangePage");
        String get_Day = getIntent().getStringExtra("Day_forChangePage");
        String get_Hours = getIntent().getStringExtra("Hours_forChangePage");
        String get_Minutes = getIntent().getStringExtra("Minutes_forChangePage");
        String get_Music = getIntent().getStringExtra("songName_forChangePage");


        setIncomingContent_ItemName02(get_Item_Name,get_Month,get_Day,get_Hours,get_Minutes,get_Music);

    }


    public void setIncomingContent_ItemName02(String ItemName,String Month,String Day, String Hours, String Minutes, String Music){

        TextView itemName_ = (TextView) findViewById(R.id.editText_itemName_ChangePage);
        TextView month_ = (TextView) findViewById(R.id.editText_month_ChangePage);
        TextView day_ = (TextView) findViewById(R.id.editText_day_ChangePage);
        TextView hours_ = (TextView) findViewById(R.id.editText_hours_ChangePage);
        TextView minutes_ = (TextView) findViewById(R.id.editText_minutes_ChangePage);
        EditText music_ = (EditText) findViewById(R.id.editText_music_ChangePage);



        itemName_.setText(ItemName);
        month_.setText(Month);
        day_.setText(Day);
        hours_.setText(Hours);
        minutes_.setText(Minutes);
        music_.setText(Music);



    }
//    public void btnConn(View view){
////        Send objSend = new Send();
////        objSend.execute("");
////
////
////        Intent intent = new Intent(Page_of_Change_Data.this, Recycler_view.class);
////        startActivity(intent);
////    }


public void showMusic_ChangePage (View view){//用于调用音乐读取


    Intent intent= new Intent(Page_of_Change_Data.this, activity_for_show_musicList.class);

    intent.putExtra("ItemName_forChangePage",Edit_Text_Insert_ItemName_ChangePage.getText().toString());
    intent.putExtra("Year_forChangePage",year_string);
    intent.putExtra("Month_forChangePage",Edit_Text_Month_ChangePage.getText().toString());
    intent.putExtra("Day_forChangePage",Edit_Text_Days_ChangePage.getText().toString());
    intent.putExtra("Hours_forChangePage",Edit_Text_Hours_ChangePage.getText().toString());
    intent.putExtra("Minutes_forChangePage",Edit_Text_Minutes_ChangePage.getText().toString());
    intent.putExtra("FormerActivityName01","Change");

    Log.d("Debug,putEtra ItemName",Edit_Text_Insert_ItemName_ChangePage.getText().toString() );
    Log.d("Debug,putEtra Month",Edit_Text_Month_ChangePage.getText().toString() );
    Log.d("Debug,putEtra Day",Edit_Text_Days_ChangePage.getText().toString() );
    Log.d("Debug,putEtra Hours",Edit_Text_Hours_ChangePage.getText().toString() );
    Log.d("Debug,putEtra Minutes",Edit_Text_Minutes_ChangePage.getText().toString() );


    startActivity(intent);

}

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);
        hourOfCalendar = hourOfDay;
        minuteOfCalendar = minute;
        TextView hours_B = (TextView) findViewById(R.id.editText_hours_ChangePage);
        TextView minutes_B = (TextView) findViewById(R.id.editText_minutes_ChangePage);
        hours_B.setText(String.valueOf(hourOfDay));
        minutes_B.setText(String.valueOf(minute));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        yearOfCalendar = year;
        monthOfCalendar = month;
        dayOfCalendar = dayOfMonth;
        TextView month_B = (TextView) findViewById(R.id.editText_month_ChangePage);
        TextView day_B = (TextView) findViewById(R.id.editText_day_ChangePage);
        year_string = String.valueOf(year);
        month_string = String.valueOf(month);
        day_string = String.valueOf(dayOfMonth);
        month_B.setText(String.valueOf(month+1));
        day_B.setText(String.valueOf(dayOfMonth));
        ifChooseHourAndMinute = true;
    }


    private class Send extends AsyncTask<String,String,String>
    {


        TextView itemName_ = (TextView) findViewById(R.id.editText_itemName_ChangePage);
        TextView month_ = (TextView) findViewById(R.id.editText_month_ChangePage);
        TextView day_ = (TextView) findViewById(R.id.editText_day_ChangePage);
        TextView hours_ = (TextView) findViewById(R.id.editText_hours_ChangePage);
        TextView minutes_ = (TextView) findViewById(R.id.editText_minutes_ChangePage);
        EditText music_ = (EditText) findViewById(R.id.editText_music_ChangePage);

        String msg = "";//设置提示message
        String itemName = itemName_.getText().toString();//生成的String用于sql语句（insert或者delete data）
        String month = month_.getText().toString();
        String days = day_.getText().toString();
        String hours = hours_.getText().toString();
        String minutes = minutes_.getText().toString();
        String musicName = music_.getText().toString();



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Mysql_result02.setText("Please wait Inserting Data");



        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                if (conn ==null){msg = "Connection goes wrong, error type01";}
                else{
                    String Music_Path = getIntent().getStringExtra("MusicPath");
                    String query = "UPDATE Items_List SET Month = '"+month+"', Day = '"+days+"', Hours = '"+hours+"', Minutes = '"+minutes+"', MusicName = '"+musicName+"'   where ItemName = '"+itemName+"';";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    msg = "Inserting Successfully!!!";

                }
                conn.close();

            }
            catch (Exception e)
            {
                msg = "Connection goes wrong,error tye02";
                e.printStackTrace();
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            Mysql_result02.setText(msg);
        }
    }









}
