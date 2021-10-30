package com.sheikh.allnews;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        SharedPreferences sharedPreferences1 = getSharedPreferences("darkmode",MODE_PRIVATE);
        Boolean isDark = sharedPreferences1.getBoolean("dark",false);
        if (isDark){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
//        status bar
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = this.getWindow();
                window.setStatusBarColor(getResources().getColor(R.color.status_splashscreen));
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                }
            },3000);
        }catch (Exception e){

        }
    }
}