package com.app.colorscramble;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.w("Activity -->",TAG);

        //creating new thread just for demonstration of background tasks
        Thread t=new Thread() {
            public void run() {

                try {
                    //sleep thread for 6 seconds
                    sleep(6000);

                    //Call Main activity
                    Intent i=new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);

                    //destroying Splash activity
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        //start thread
        t.start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}