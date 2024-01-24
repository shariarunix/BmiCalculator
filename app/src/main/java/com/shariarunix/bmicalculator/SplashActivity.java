package com.shariarunix.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ImageView imgAppLogo;
    TextView txtAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgAppLogo = findViewById(R.id.img_app_logo);
        txtAppName = findViewById(R.id.txt_app_name);

        Animation appLogoAnim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.logo_fade_in);
        imgAppLogo.startAnimation(appLogoAnim);

        Animation appNameAnim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.name_fade_in);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtAppName.setVisibility(View.VISIBLE);
                txtAppName.startAnimation(appNameAnim);
            }
        }, 250);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);

    }
}