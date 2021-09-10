package com.sheikh.sqlitep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Controller.DatabaseHandler;
import Model.Person;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);
            db.addPerson(new Person("Sheikh","El Moctar","Dubia",16));
            db.addPerson(new Person("Jidou","Muhammed Lemine","Nouakchott",18));
            db.addPerson(new Person("Bouna","Ahmed","Nouakchott",17));
            db.addPerson(new Person("Muhammed","Essa","baghdad",37));
        Person p = db.getPerson("Bouna");

            String info = " Name: "+ p.getName() + " Last Name: "+ p.getLname() + " Address: "+ p.getAddress() + " Age: "+ p.getAge();
            Log.d("Data===> ",info);


    }
}
