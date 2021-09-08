package com.sheikh.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.Listitem;

public class MainActivity extends AppCompatActivity {
    private List<Listitem> listitems;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();
        // for (int a = 1; a<15; a++){
        //    Listitem item = new Listitem("Sheikh EL-Moctar","He is a Software Engineer","7/9/2021");
        //    listitems.add(item);
        //}
        Listitem item1 = new Listitem("Sheikh El-Moctar","Software Engineer","2004");
        Listitem item2 = new Listitem("Jidou Muhammed Lemine","Player Succor","2003");
        Listitem item3 = new Listitem("Yameen","Web Developer","2002");
        Listitem item4 = new Listitem("Assmea","Teacher English","2002");
        Listitem item5 = new Listitem("Muhammed Salem","Docter","2004");
        listitems.add(item1);
        listitems.add(item2);
        listitems.add(item3);
        listitems.add(item4);
        listitems.add(item5);

        adapter = new MyAdapter(this,listitems);
        recyclerView.setAdapter(adapter);
    }
}
