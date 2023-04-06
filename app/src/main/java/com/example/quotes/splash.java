package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class splash extends AppCompatActivity {
TextView txt;
CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txt=findViewById(R.id.text);
        Intent ihome=new Intent(splash.this,MainActivity.class);//intent object
        //for lottie file
//        LottieAnimationView lattview;
//        lattview=findViewById(R.id.lottie);
//        lattview.setAnimation(R.raw.lottie);//setting lottie animation
//        lattview.playAnimation();//playing lottie animation
//        lattview.loop(true);//stoping conticous moving lottie file
        Animation ani= AnimationUtils.loadAnimation(splash.this,R.anim.animation);//for text animation
        txt.startAnimation(ani);
        //this handler used to  delayed activity for second
        //we can use this handler anywhere for time delayed
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(ihome);

                finish();//this is used to pop out activity from stack
                //means ,during backward direction we stopped splash activity;
            }
        },5000);//setting time in milli second (5 sec. delayed)





    }
}