package com.sheikh.imagedetails;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
    ImageView img;
    TextView name,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        img = findViewById(R.id.imageView);
        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String n = extras.getString("name");
            String d = extras.getString("desc");
            info(n,d);
        }

    }
    public void info(String name1,String desc1){
        if (name1.equals("Android")){
            img.setImageDrawable(getResources().getDrawable(R.drawable.android_img));
            name.setText(name1);
            desc.setText(desc1);
        } else if(name1.equals("Java")){
            img.setImageDrawable(getResources().getDrawable(R.drawable.java_img));
            name.setText(name1);
            desc.setText(desc1);
        }
    }
}
