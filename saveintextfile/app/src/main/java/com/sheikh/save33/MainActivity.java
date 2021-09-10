package com.sheikh.save33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;
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
                if (!editText.getText().toString().isEmpty()){

                    try {
                        writeToTxt(editText.getText().toString());
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        try {
            if (readData() != null){
                textView.setText(readData());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToTxt(String text) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("text.txt", Context.MODE_PRIVATE));
        outputStreamWriter.write(text);
        outputStreamWriter.close();
    }
    public String readData() throws IOException {
        String myText = "";
        InputStream inputStream = openFileInput("text.txt");
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String temData = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((temData = bufferedReader.readLine()) != null){
                stringBuilder.append(temData);
            }
            inputStream.close();
            myText = stringBuilder.toString();
        }
        return myText;
    }
}
