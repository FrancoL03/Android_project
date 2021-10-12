package com.example.vulcan.test01;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.*;
import android.view.View;
import android.widget.EditText;
import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import android.content.Intent;
import android.view.View.OnClickListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.Clock;
import java.util.Calendar;

//This activity is for creating an alarm item. However, the class name here is not relative, we are sorry for this inconvenience.
public class volley_json_parse_test extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

  private TextView textView_Parse_Result;
  private RequestQueue mQueue;
//  private TextView Mysql_result;
  private Calendar c = Calendar.getInstance();

  private int yearOfCalendar;
  private int monthOfCalendar;
  private int dayOfCalendar;
  private String year_string = null;
  private String month_string = null;
  private String day_string = null;
  private String hours_string = null;
  private String minutes_string = null;




  private int hourOfCalendar ;
  private int minuteOfCalendar;

  private String MusicName_s;
  private String itemName_s;

  private boolean ifChooseHourAndMinute = false;
    private boolean ifChooseMonthAndDay = false;

  private static final String DB_URL = "jdbc:mysql://mysql.studev.groept.be:3306/a18_sd608"  ;                  //"jdbc:mysql://10.46.194.7/employee101";//设置数据库的url
  private static final String USER = "a18_sd608";                                                                  //数据库名
  private static final String PASS = "a18_sd608";

  private EditText Edit_Text_Insert_ItemName_CreatePage;
  private TextView Edit_Text_Month_CreatePage;
  private TextView Edit_Text_Days_CreatePage;
  private TextView Edit_Text_Hours_CreatePage;
  private TextView Edit_Text_Minutes_CreatePage;
  private EditText Edit_Text_MusicName_CreatePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_json_parse_test);

        Button alarmButton = (Button) findViewById(R.id.button_alarm);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });
        Button dateButton = (Button) findViewById(R.id.button_date_create);
        dateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        getIncomingContent();

//        textView_Parse_Result = findViewById(R.id.textView_Parse_Result);
//        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);//请求队列对象
        String a = "ss";
//        buttonParse.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//                jsonParse();//调用一个json方法
//            }
//
//
//        });
/////////////////////////////////////////////////////////////////////////
//        Mysql_result = (TextView)findViewById(R.id.Textview_Msql_result);


        Edit_Text_Insert_ItemName_CreatePage = (EditText)findViewById(R.id.Edit_Text_Insert_ItemName_CreatePage);
        Edit_Text_Month_CreatePage = (TextView)findViewById(R.id.Edit_Text_Month_CreatePage);
        Edit_Text_Days_CreatePage    = (TextView)findViewById(R.id.Edit_Text_Days_CreatePage);
        Edit_Text_Hours_CreatePage   = (TextView)findViewById(R.id.Edit_Text_Hours_CreatePage);
        Edit_Text_Minutes_CreatePage = (TextView)findViewById(R.id.Edit_Text_Minutes_CreatePage);
        Edit_Text_MusicName_CreatePage   = (EditText)findViewById(R.id.Edit_Text_MusicName_CreatePage);


        month_string = Edit_Text_Month_CreatePage.getText().toString();
        day_string = Edit_Text_Days_CreatePage.getText().toString();
        hours_string = Edit_Text_Hours_CreatePage.getText().toString();
        minutes_string = Edit_Text_Minutes_CreatePage.getText().toString();

        itemName_s = Edit_Text_Insert_ItemName_CreatePage.getText().toString();
        MusicName_s = Edit_Text_MusicName_CreatePage.getText().toString();


//        final Button shiftButton = findViewById(R.id.button_insert);//link this button to the shift button in design view, to get to a new page.
//        shiftButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = new Intent(volley_json_parse_test.this, First_Page.class);
//                startActivity(intent);                             //Launch a new activity. You will not receive any information about when the activity exits.
//                // The method being which is excecuted when press
//                //Code here execute on main thread after user press this button
//            }
//        });









///////////////////////////////////////////////////////////////////////////
     }

    public void getIncomingContent(){


        String get_Item_Name = getIntent().getStringExtra("ItemName_forCreatePage");
        year_string = getIntent().getStringExtra("Year_forCreatePage");
        String get_Month = getIntent().getStringExtra("Month_forCreatePage");
        String get_Day = getIntent().getStringExtra("Day_forCreatePage");
        String get_Hours = getIntent().getStringExtra("Hours_forCreatePage");
        String get_Minutes = getIntent().getStringExtra("Minutes_forCreatePage");
        String get_Music = getIntent().getStringExtra("songName_forCreatePage");


        setIncomingContent(get_Item_Name,get_Month,get_Day,get_Hours,get_Minutes,get_Music);

    }

    public void setIncomingContent(String ItemName,String Month,String Day, String Hours, String Minutes, String Music){

        EditText itemName_ = (EditText) findViewById(R.id.Edit_Text_Insert_ItemName_CreatePage);
        TextView month_ = (TextView) findViewById(R.id.Edit_Text_Month_CreatePage);
        TextView day_ = (TextView) findViewById(R.id.Edit_Text_Days_CreatePage);
        TextView hours_ = (TextView) findViewById(R.id.Edit_Text_Hours_CreatePage);
        TextView minutes_ = (TextView) findViewById(R.id.Edit_Text_Minutes_CreatePage);
        EditText music_ = (EditText) findViewById(R.id.Edit_Text_MusicName_CreatePage);


        itemName_.setText(ItemName);
        month_.setText(Month);
        day_.setText(Day);
        hours_.setText(Hours);
        minutes_.setText(Minutes);
        music_.setText(Music);

    }









/////////////////////////////////////////////////////////////////////////////////////////
//    //此方法用于获取Mysql database中的json格式数据（query result）
//     private void jsonParse(){
//
//        String url = "https://studev.groept.be/api/a18_sd608/Items_List"; //调用json result 的网络链接
//
//         JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                 new Response.Listener<JSONObject>() {
//                     @Override
//                     public void onResponse(JSONObject response) {
//                         //这个子方法在request成功时运行
//                         try {
//                             JSONArray jsonArray = response.getJSONArray("employees");
//                         //                                     获取value值      key
//                             for(int i=0; i<jsonArray.length();i++){
//                                  JSONObject employee = jsonArray.getJSONObject(i);
//                                  String firstName = employee.getString("firstname");
//                                  int age = employee.getInt("age");
//                                  String mail = employee.getString("mail");
//                                 textView_Parse_Result.append(firstName + "," + String.valueOf(age) + "," + mail + "\n\n");
//
//                             }
//
//
//                         } catch (JSONException e) {
//                             e.printStackTrace();
//                         }
//                     }
//                 }, new Response.ErrorListener() {
//             @Override
//             public void onErrorResponse(VolleyError error) {
//                 //这个子方法在request失败时运行
//                   error.printStackTrace();
//             }
//         });
//
//         mQueue.add(request);
//
//
//    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
//此方法用于向Mysql database中写入数据（存入信息）
public void btnConn(View view) {

    boolean check = true;



    if (month_string== null || month_string.length() == 0) {
        check = false;
    }

    if (day_string== null || day_string.length() == 0) {
        check = false;
    }

    if (hours_string== null || hours_string.length() == 0) {
        check = false;
    }

    if (minutes_string== null || minutes_string.length() == 0) {
        check = false;
    }


    if (itemName_s== null || itemName_s.length() == 0) {
        check = false;
    }



    if (MusicName_s == null || MusicName_s.length() == 0) {
        check = false;
    }

    if (check == true) {

    int month_ = Integer.parseInt(Edit_Text_Month_CreatePage.getText().toString());
    int month_minus_1 = month_ - 1;
    String month_string = String.valueOf(month_minus_1);


    day_string = Edit_Text_Days_CreatePage.getText().toString();
    hours_string = Edit_Text_Hours_CreatePage.getText().toString();
    minutes_string = Edit_Text_Minutes_CreatePage.getText().toString();
    itemName_s = Edit_Text_Insert_ItemName_CreatePage.getText().toString();
    MusicName_s = Edit_Text_MusicName_CreatePage.getText().toString();

    Intent intent_Alarm_Create = new Intent(volley_json_parse_test.this, Alarm.class);
    intent_Alarm_Create.putExtra("pathForAlarm", getIntent().getStringExtra("MusicPath"));
    String a = Edit_Text_Insert_ItemName_CreatePage.getText().toString();
    intent_Alarm_Create.putExtra("itemNameForAlarmFromCreate", Edit_Text_Insert_ItemName_CreatePage.getText().toString());

    intent_Alarm_Create.putExtra("yearForAlarm", year_string);
    intent_Alarm_Create.putExtra("monthForAlarm", month_string);
    intent_Alarm_Create.putExtra("dayForAlarm", day_string);
    intent_Alarm_Create.putExtra("hourForAlarm", hours_string);
    intent_Alarm_Create.putExtra("minuteForAlarm", minutes_string);


    Alarm alarm = new Alarm(c, getIntent().getStringExtra("MusicPath"), Edit_Text_Insert_ItemName_CreatePage.getText().toString());
//    alarm.startAlarm();
    String alarm_name = Edit_Text_Insert_ItemName_CreatePage.getText().toString();
    int map_size = AlarmList.size();
    AlarmList.addAlarm(alarm_name,map_size);
    Log.d("Alarm map",AlarmList.show_map().toString());
//    Intent intent_R = new Intent(volley_json_parse_test.this,Recycler_view.class);

    Send objSend = new Send();
    objSend.execute("");

//        Intent[] intents = new Intent[2];
//        intents[0] =  intent_Alarm;
//        intents[1] = intent_R;


    startActivity(intent_Alarm_Create);


////        Intent intent_AlarmReceiver = new Intent(volley_json_parse_test.this, AlarmReceiver.class);
//
//        Intent[] intents = new Intent[2];
//        intents[0] = intent_AlarmReceiver;
//        intents[1] = intent_Recycler_view;
//
//        String hours_Alarm = Edit_Text_Hours.getText().toString();
//        String minutes_Alarm = Edit_Text_Minutes.getText().toString();
//
//        intent_AlarmReceiver.putExtra("hours_Alarm",hours_Alarm);
//        intent_AlarmReceiver.putExtra("minutes_Alarm",minutes_Alarm);

//        startActivity(intent_Recycler_view);
          }
       else{ Toast.makeText(this,"Please fill in all the data! Or choose the music again!",Toast.LENGTH_LONG).show();}


    }

    public void showMusic (View view){


        Intent intent= new Intent(volley_json_parse_test.this, activity_for_show_musicList.class);

        intent.putExtra("ItemName_forCreatePage",Edit_Text_Insert_ItemName_CreatePage.getText().toString());
        intent.putExtra("Year_forCreatePage",year_string);
        intent.putExtra("Month_forCreatePage",Edit_Text_Month_CreatePage.getText().toString());
        intent.putExtra("Day_forCreatePage",Edit_Text_Days_CreatePage.getText().toString());
        intent.putExtra("Hours_forCreatePage",Edit_Text_Hours_CreatePage.getText().toString());
        intent.putExtra("Minutes_forCreatePage",Edit_Text_Minutes_CreatePage.getText().toString());
        intent.putExtra("FormerActivityName01","Create");

        Log.d("Debug,putEtra ItemName",Edit_Text_Insert_ItemName_CreatePage.getText().toString() );
        Log.d("Debug,putEtra Month",Edit_Text_Month_CreatePage.getText().toString() );
        Log.d("Debug,putEtra Day",Edit_Text_Days_CreatePage.getText().toString() );
        Log.d("Debug,putEtra Hours",Edit_Text_Hours_CreatePage.getText().toString() );
        Log.d("Debug,putEtra Minutes",Edit_Text_Minutes_CreatePage.getText().toString() );


        startActivity(intent);

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);
        hourOfCalendar = hourOfDay;
        minuteOfCalendar = minute;
        TextView hours_A = (TextView) findViewById(R.id.Edit_Text_Hours_CreatePage);
        TextView minutes_A = (TextView) findViewById(R.id.Edit_Text_Minutes_CreatePage);
        hours_A.setText(String.valueOf(hourOfDay));
        minutes_A.setText(String.valueOf(minute));
        ifChooseHourAndMinute = true;

//        Edit_Text_Hours_CreatePage.setText(hourOfDay);
//        Edit_Text_Minutes_CreatePage.setText(minute);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        yearOfCalendar = year;
        monthOfCalendar = month;
        dayOfCalendar = dayOfMonth;
        TextView month_A = (TextView) findViewById(R.id.Edit_Text_Month_CreatePage);
        TextView day_A = (TextView) findViewById(R.id.Edit_Text_Days_CreatePage);
        year_string = String.valueOf(year);
        month_string = String.valueOf(month);
        day_string = String.valueOf(dayOfMonth);
        month_A.setText(String.valueOf(month+1));
        day_A.setText(String.valueOf(dayOfMonth));
        ifChooseMonthAndDay = true;
    }

//    public void startAlarm(Calendar c){
//        AlertReceiver alertReceiver01 = new AlertReceiver();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("com.fengchi.broadcastreceiver.MYRECEIVER");
//        registerReceiver(alertReceiver01, intentFilter);
//        Intent intent1 = new Intent("com.fengchi.broadcastreceiver.MYRECEIVER");
//        intent1.putExtra("Music_Path",path);         //向广播接收器传递数据
//        sendBroadcast(intent1);

//        String path = getIntent().getStringExtra("MusicPath");
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlertReceiver.class);
//        intent.putExtra("Music_Path",path);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
//        if(c.before((Calendar.getInstance()))){
//            c.add(Calendar.DATE,1);
//        }
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pendingIntent);
//    }


    private class Send extends AsyncTask<String,String,String>
{
    String msg = "";//设置提示message
    String itemName = Edit_Text_Insert_ItemName_CreatePage.getText().toString();//生成的String用于sql语句（insert或者delete data）
    String month = Edit_Text_Month_CreatePage.getText().toString();
    String days = Edit_Text_Days_CreatePage.getText().toString();
    String hours = Edit_Text_Hours_CreatePage.getText().toString();
    String minutes = Edit_Text_Minutes_CreatePage.getText().toString();
    String musicName = Edit_Text_MusicName_CreatePage.getText().toString();



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        Mysql_result.setText("Please wait Inserting Data");



    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            if (conn ==null){msg = "Connection goes wrong, error type01";}
            else{
                String Music_Path = getIntent().getStringExtra("MusicPath");

                String query = "INSERT INTO Items_List (ItemName,Month,Day,Hours,Minutes,MusicName) VALUES('"+itemName+"','"+month+"','"+days+"','"+hours+"','"+minutes+"','"+musicName+"')";
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

//    @Override
//    protected void onPostExecute(String msg) {
//        Mysql_result.setText(msg);
//    }
}

}



