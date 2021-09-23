package com.example.weatherapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread myThread= new Thread(){
            @Override
            public void run(){
                try{
                    sleep(5000);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

    }
}