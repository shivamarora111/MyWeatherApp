package com.example.shivam.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashScreen extends AppCompatActivity {

TextView textView;
ImageView imageView4;
    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        textView=(TextView)findViewById(R.id.textView);
        imageView4=(ImageView)findViewById(R.id.imageView4);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView2.animate().rotation(3600f).setDuration(4500);
        imageView4.animate().alpha(1f).setDuration(4000);
        textView.setTranslationX(-1000f);
        textView.animate().translationXBy(1000f).setDuration(2000);


        Handler myHandler = new Handler();
                myHandler.postDelayed(new Runnable() {
                    public void run() {

                startActivity(new Intent(getApplicationContext(),MainActivity1.class));
                finish();
                    }},5000);
                }

    }

