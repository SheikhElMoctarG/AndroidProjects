package com.sheikh.activitytoautheractivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonSecond, buttonThird;
    private  final int REQUEST_CODE = 1;
    private  final int REQUEST_CODE2 = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSecond = findViewById(R.id.buttonSecond);
        buttonThird = findViewById(R.id.buttonThird);
        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("name","Sheikh El-Moctar");
                intent.putExtra("age","16");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        buttonThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
                intent.putExtra("name","Taqee El-Moctar");
                intent.putExtra("age","23");
                startActivityForResult(intent,REQUEST_CODE2);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String returnData = data.getStringExtra("welcome");
                Toast.makeText(getApplicationContext(), returnData, Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == REQUEST_CODE2){
            if (resultCode == RESULT_OK) {
                String returnData = data.getStringExtra("welcome");
                Toast.makeText(getApplicationContext(), returnData, Toast.LENGTH_LONG).show();
            }
        }
    }
}
