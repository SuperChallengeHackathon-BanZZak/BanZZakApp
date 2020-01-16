package com.greentea.banzzak;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar left2right, right2left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        left2right = findViewById(R.id.left2right);
        right2left = findViewById(R.id.right2left);
        right2left.setRotation(180);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        getWindow().setStatusBarColor(getColor(R.color.title_color));
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
//        getWindow().setNavigationBarColor(getColor(R.color.title_color));

//        Handler handler = new Handler();
//        handler.postDelayed(new Splash(), 2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startActivity(new Intent(getApplication(), MainActivity.class));
                finish();
            }
        }).start();
    }

    private void doWork(){

        left2right.setProgressTintList(ColorStateList.valueOf(getColor(R.color.black)));
        left2right.setProgressBackgroundTintList(ColorStateList.valueOf(getColor(R.color.title_color)));
//        left2right.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.title_color)));
        right2left.setProgressTintList(ColorStateList.valueOf(getColor(R.color.theme)));
//        right2left.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
        right2left.setProgressBackgroundTintList(ColorStateList.valueOf(getColor(R.color.title_color)));

        for (int progress=0; progress<100; progress++) {
            try {
                Thread.sleep(15);
                left2right.setProgress(progress);
                right2left.setProgress(progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
