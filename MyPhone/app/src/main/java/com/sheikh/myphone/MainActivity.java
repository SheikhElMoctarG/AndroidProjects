package com.sheikh.myphone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextPhone);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String number = editText.getText().toString();
                if (!TextUtils.isEmpty(number)){
                    String dial = "tel:"+number;
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                } else{
                    Toast.makeText(getApplicationContext(),"please put a number phone",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (Build.VERSION.SDK_INT >= 23){
            if (checkPermission()){
                Toast.makeText(getApplicationContext(),"permission granted",Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }

        }

    }
    public Boolean checkPermission(){
        int callPermessionResult = ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE);
        return callPermessionResult == PackageManager.PERMISSION_GRANTED;
    }
    public void requestPermission(){
       ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    Boolean callPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (callPermission) {
                        Toast.makeText(getApplicationContext(), "Permission accepted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                        button.setEnabled(false);
                    }
                }
                break;
        }
    }
}