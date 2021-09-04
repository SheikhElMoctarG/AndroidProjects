package com.sheikh.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    CheckBox assma,sheikh,jidou,bouna;
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        assma = findViewById(R.id.assma);
        sheikh = findViewById(R.id.sheikh);
        jidou = findViewById(R.id.jidou);
        bouna = findViewById(R.id.bouna);
        check = findViewById(R.id.check);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("you checked ");
                if (assma.isChecked()){
                    stringBuilder.append(assma.getText().toString()+" and ");
                }
                if (sheikh.isChecked()){
                    stringBuilder.append(sheikh.getText().toString()+" and ");
                }
                if (jidou.isChecked()){
                    stringBuilder.append(jidou.getText().toString()+" and ");
                }
                if (bouna.isChecked()){
                    stringBuilder.append(bouna.getText().toString());
                }

                textView.setText(stringBuilder);
            }
        });
    }
}
