package com.sheikh.allnews;

import static com.sheikh.allnews.R.drawable.icon_lite;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
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