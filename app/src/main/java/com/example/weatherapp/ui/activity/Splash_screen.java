package com.example.weatherapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.R;

public class Splash_screen extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        textView = findViewById(R.id.name);
        imageView = findViewById(R.id.meteo);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        imageView.startAnimation(animation);
        textView.startAnimation(animation);

        Thread myThread= new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3500);
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