package com.example.sqlite;

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
//            db.addPerson(new Person(1,"Sheikh El Moctar",16,"Dubia"));
//            db.addPerson(new Person(2,"Jidou Muhammed Lemine",18,"Nouakchott"));
//            db.addPerson(new Person(3,"Bouna Ahmed",17,"Nouakchott"));
//            db.addPerson(new Person(4,"Muhammed Essa",36,"baghdad"));
//        List<Person> personList = db.getPeople();
//        for (Person p : personList) {
//            String info = "ID: "+p.getId()+ " Name: "+ p.getName() + " Address: "+ p.getAddress() + " Age: "+ p.getAge();
//            Log.d("Data===> ",info);
//        }
//        Person p2 = db.getPerson(2);
//        db.deletePerson(p2);
//        Person p = db.getPerson(3);
//        String info = "ID: "+p.getId()+ " Name: "+ p.getName() + " Address: "+ p.getAddress() + " Age: "+ p.getAge();
//        Log.d("before update===> ",info);
//        p.setName("Taqi Usef");
//        p.setAge(23);
//        int p2 = db.updatePerson(p);
//        String info2 = "ID: "+p.getId()+ " Name: "+ p.getName() + " Address: "+ p.getAddress() + " Age: "+ p.getAge();
//        Log.d("After update===> ",info2);
        Log.d("personNumber", String.valueOf(db.getNumPerson()));

    }
}
