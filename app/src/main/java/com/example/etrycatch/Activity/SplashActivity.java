package com.example.etrycatch.Activity;

import androidx.appcompat.R;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;


import com.example.etrycatch.Adapters.StreamPogo;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    public static int splash_timeot = 3000;
    private List<StreamPogo> mainPgos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        Handler abc = new Handler();

        abc.postDelayed(new Runnable() {

            @Override
            public void run() {


                Intent in = new Intent(SplashActivity.this,SelectionActivity.class);
                startActivity(in);
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);



            }
        }, splash_timeot);
    }
}