package com.example.jarvis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandle(), 3000);
    }

    private class splashhandle implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), BlueToothActivity.class));
            SplashActivity.this.finish();
        }
    }
    public void onBackPressed(){

    }
}

