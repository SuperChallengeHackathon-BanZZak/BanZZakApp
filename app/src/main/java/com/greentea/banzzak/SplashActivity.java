package com.greentea.banzzak;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        getWindow().setStatusBarColor(getColor(R.color.title_color));
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
//        getWindow().setNavigationBarColor(getColor(R.color.title_color));

        Handler handler = new Handler();
        handler.postDelayed(new Splash(), 2000);
    }

    private  class Splash implements Runnable{
        @Override
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class));
            SplashActivity.this.finish();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
