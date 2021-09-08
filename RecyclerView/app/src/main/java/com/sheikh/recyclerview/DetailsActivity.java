package com.sheikh.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView title,description,date;
    private Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        extras = getIntent().getExtras();

        title.setText(extras.getString("title"));
        description.setText(extras.getString("description"));
        date.setText(extras.getString("date"));
    }
}
