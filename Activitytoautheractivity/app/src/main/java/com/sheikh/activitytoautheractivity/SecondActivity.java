package com.sheikh.activitytoautheractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView name,age;
    Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        buttonBack = findViewById(R.id.buttonBack);
        Bundle extras = getIntent().getExtras();
        name.setText(extras.getString("name"));
        age.setText(extras.getString("age"));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent extraBack = getIntent();
                extraBack.putExtra("welcome","I am from SecondActivity");
                setResult(RESULT_OK,extraBack);
                finish();
            }
        });
    }
}
