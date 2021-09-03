package com.sheikh.radiobutton;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        radioGroup = findViewById(R.id.radioFood);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedID) {
                radioButton = findViewById(checkedID);
                switch (radioButton.getId()){
                    case R.id.pizzaID:{
                        textView.setText(R.string.pizza);
                        Log.d("log id","pizza");
                        break;
                    }
                    case R.id.humbergerID:{
                        textView.setText(R.string.humberger);
                        Log.d("log id","humberger");
                        break;
                    }
                    case R.id.steakID:{
                        textView.setText(R.string.steak);
                        Log.d("log id","steak");
                        break;
                    }
                }
            }
        });
    }
}
