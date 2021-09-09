package com.sheikh.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView title,desc,year;
    private ImageView img;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        img = findViewById(R.id.imageView);
        year = findViewById(R.id.year);
        extras = getIntent().getExtras();

        title.setText(extras.getString("title"));
        desc.setText(extras.getString("desc"));
        year.setText(extras.getString("year"));
        img.setImageResource(extras.getInt("cover"));
    }
}
