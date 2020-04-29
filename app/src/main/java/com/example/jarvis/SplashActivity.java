package com.example.jarvis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    private Timer timer;
    private ProgressBar progessBar;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_splash);

        progessBar = findViewById(R.id.progressBar);
        progessBar.setProgress(0);
        progessBar.getProgressDrawable().setColorFilter(
                Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);

        final long period = 30;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (i < 100) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    progessBar.setProgress(i);
                    i++;
                } else {
                    timer.cancel();
                    Intent intent = new Intent(SplashActivity.this, BlueToothActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 0, period);
    }

    public void onBackPressed() {

    }
}

