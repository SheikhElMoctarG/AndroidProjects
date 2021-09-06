package com.sheikh.activitytoautheractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView name,age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);

        Bundle extras = getIntent().getExtras();
        name.setText(extras.getString("name"));
        age.setText(extras.getString("age"));
    }
}
