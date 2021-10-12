package com.example.vulcan.test01;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AlertController;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

//Creating the RecyclerView to holds the alarm items
public class Recycler_view extends AppCompatActivity {//This is the page for recyclerView

    private static final String URL_DATA = "https://studev.groept.be/api/a18_sd608/Items_List";




    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadRecyclerViewData();
        final Button button_create_newItem = findViewById(R.id.Button_create_newItem);//用于shift to the new page
        button_create_newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Recycler_view.this, volley_json_parse_test.class);
                startActivity(intent);


            }
        });


        final Button button_refresh = findViewById(R.id.button_refresh);//用于刷新页面
        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent intent = new Intent(Recycler_view.this, Recycler_view.class);
                startActivity(intent);


            }
        });






//        final Button shiftButton = findViewById(R.id.button_changeData);//link this button to the shift button in design view, to get to a new page.
//        shiftButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Recycler_view.this, volley_json_parse_test.class);
//                startActivity(intent);                             //Launch a new activity. You will not receive any information about when the activity exits.
//                // The method being which is excecuted when press
//                //Code here execute on main thread after user press this button
//            }
//        });




    }







    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();


        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressDialog.dismiss();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject movie = response.getJSONObject(i);
                                String itemName = movie.getString("ItemName");
                                String month = movie.getString("Month");
                                String day = movie.getString("Day");
                                String hours = movie.getString("Hours");
                                String minutes = movie.getString("Minutes");
                                String musicName = movie.getString("MusicName");

                                Log.d("itemName", itemName);//Send a DEBUG log message.
                                Log.d("Month", month);
                                Log.d("Day", day);
                                Log.d("Hours", hours);
                                Log.d("Minutes", minutes);
                                Log.d("MusicName", musicName);


                                ListItem item = new ListItem(itemName, month, day, hours, minutes, musicName);
                                listItems.add(item);
                                adapter = new MyAdapter(listItems, getApplicationContext());
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String s) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(s);
//
//                            JSONArray array = jsonObject.getJSONArray("")
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {//用于截取错误，如果有错误的话会执行
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });









}
