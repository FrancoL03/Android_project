package com.example.vulcan.test01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

//This is an activity for the welcoming page which holds for 3 seconds, when you open the app (logo)
public class SplashPage extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;//设置延迟时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashPage.this, First_Page.class);
                startActivity(intent);
                finish();

            }
        }, SPLASH_TIME_OUT);


    }


}
