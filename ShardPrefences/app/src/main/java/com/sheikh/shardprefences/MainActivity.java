package com.sheikh.shardprefences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;
    SharedPreferences sharedPreferences;
    String KEY = "KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText.getText());
                sharedPreferences = getSharedPreferences(KEY,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("text",editText.getText().toString());
                editor.commit();
            }
        });
        SharedPreferences preferences = getSharedPreferences(KEY,0);
        String msg = preferences.getString("text","");
        textView.setText(msg);
    }
}
