package com.sheikh.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText value1,value2;
    Button plus,minus,multi,divi;
    int v1;
    int v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        value1 = findViewById(R.id.value1);
        value2 = findViewById(R.id.value2);
        plus = findViewById(R.id.buttonPlus);
        minus = findViewById(R.id.buttonMinus);
        multi = findViewById(R.id.buttonMulti);
        divi = findViewById(R.id.buttonDivi);
        v1 = Integer.parseInt(value1.getText().toString());
        v2 = Integer.parseInt(value2.getText().toString());
        plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    v1 = Integer.parseInt(value1.getText().toString());
                    v2 = Integer.parseInt(value2.getText().toString());
                    if (v1 != 0 && v2 !=0) {
                        int tot = v1 + v2;
                        String tots = String.valueOf(tot);
                        result.setText(tots);
                    }
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    v1 = Integer.parseInt(value1.getText().toString());
                    v2 = Integer.parseInt(value2.getText().toString());
                    if (v1 != 0 && v2 !=0) {
                        int tot = v1 - v2;
                        String tots = String.valueOf(tot);
                        result.setText(tots);
                    }
                }
            });
            multi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    v1 = Integer.parseInt(value1.getText().toString());
                    v2 = Integer.parseInt(value2.getText().toString());
                    if (v1 != 0 && v2 !=0) {
                        int tot = v1 * v2;
                        String tots = String.valueOf(tot);
                        result.setText(tots);
                    }
                }
            });
            divi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    v1 = Integer.parseInt(value1.getText().toString());
                    v2 = Integer.parseInt(value2.getText().toString());
                    if (v1 != 0 && v2 !=0) {
                        int tot = v1 / v2;
                        String tots = String.valueOf(tot);
                        result.setText(tots);
                    }
                }
            });

    }
}
