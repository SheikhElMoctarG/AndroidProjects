package com.sheikh.changetextofclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    int id = 0;
    String[] names = {"Sheikh", "Taqee", "Jidou","Bouna","Emad"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView  = findViewById(R.id.textview);
        button  = findViewById(R.id.buttonChange);
        textView.setText("Hi "+names[id]);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id ==4){
                    id = 0;
                    textView.setText("Hi "+names[id]);
                } else{
                    id +=1;
                    textView.setText("Hi "+names[id]);
                }
            }
        });
    }
}
