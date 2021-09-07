package com.sheikh.imagedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView androidimg,javaimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidimg = findViewById(R.id.android_image);
        javaimg = findViewById(R.id.java_image);
        androidimg.setOnClickListener(this);
        javaimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.android_image:
               // Toast.makeText(this,"Android", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(MainActivity.this,InfoActivity.class);
                intent1.putExtra("name","Android");
                intent1.putExtra("desc","it is oprating system");
                startActivity(intent1);
                break;
            case R.id.java_image:
               // Toast.makeText(this,"Javva", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(MainActivity.this,InfoActivity.class);
                intent2.putExtra("name","Java");
                intent2.putExtra("desc","it is language programming");
                startActivity(intent2);
                break;
        }
    }
}
