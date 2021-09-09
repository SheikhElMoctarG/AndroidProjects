package com.sheikh.mymovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import Controller.MyAdapter;
import Model.Movie;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewID);

        myAdapter = new MyAdapter(movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        Movie item1 = new Movie("Birds of Prey","Action | Adventure | Crime","2020",R.drawable.i1);
        movieList.add(item1);
        Movie item2 = new Movie("The Lodge","Drama | Horror | Thriller","2019",R.drawable.i2);
        movieList.add(item2);
        Movie item3 = new Movie("Shikara","Drama | History | Romance","2020",R.drawable.i4);
        movieList.add(item3);
        Movie item4 = new Movie("Malang","Action | Romance","2019",R.drawable.i3);
        movieList.add(item4);
        myAdapter.notifyDataSetChanged();
    }
}
