package com.example.vulcan.test01;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Page_For_Delete_Item extends AppCompatActivity {


    private static final String DB_URL = "jdbc:mysql://mysql.studev.groept.be:3306/a18_sd608"  ;                  //"jdbc:mysql://10.46.194.7/employee101";//设置数据库的url
    private static final String USER = "a18_sd608";                                                                  //数据库名
    private static final String PASS = "a18_sd608";

    private String delete_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.page_for_delete_item);
        getIncomingContent_ForAll_DeletePage();
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        final Button button_NoDelete = findViewById(R.id.button_Delete_No);//用于shift to the new page
        button_NoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Page_For_Delete_Item.this, Recycler_view.class);
                startActivity(intent);


            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////

        final Button button_YesDelete_ = findViewById(R.id.button_Delete_Yes);//用于shift to the new page
        button_YesDelete_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send objSend = new Send();
                objSend.execute("");
//                AlarmList.removeAlarm(getIntent().getStringExtra("ItemName"));

                int delete_Alarm_id = AlarmList.show_map().get(delete_name);
                Log.d("Alarm delete id",String.valueOf(delete_Alarm_id));
                AlarmList.show_map().remove(delete_name);
                Log.d("Alarm map",AlarmList.show_map().toString());

                Intent intent = new Intent(Page_For_Delete_Item.this, Recycler_view.class);
                startActivity(intent);


            }
        });

    }
    //以上为onCreate 方法
//////////////////////////////////////////////////////////////////////////////////////////////////



    public void getIncomingContent_ForAll_DeletePage(){

        String get_Item_Name = getIntent().getStringExtra("ItemName");//根据key值来索引value值


        setIncomingContent_ItemName_DeletePage(get_Item_Name);

    }

    public void setIncomingContent_ItemName_DeletePage(String ItemName){

        TextView itemName_ = (TextView) findViewById(R.id.textView_ItemName_DeletePage);

        itemName_.setText("Item Name: " + ItemName);

    }










    private class Send extends AsyncTask<String,String,String> {




        String msg = "";//设置提示message
        String get_Item_Name02 = getIntent().getStringExtra("ItemName");//用于获取primary key




        @Override
        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                if (conn == null) {
                    msg = "Connection goes wrong, error type01";
                } else {
                    delete_name = get_Item_Name02;
                    String query = "DELETE FROM Items_List WHERE ItemName = '"+get_Item_Name02+"';";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    msg = "Inserting Successfully!!!";

                }
                conn.close();

            } catch (Exception e) {
                msg = "Connection goes wrong,error tye02";
                e.printStackTrace();
            }

            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
//            Mysql_result02.setText(msg);
        }


    }






}
