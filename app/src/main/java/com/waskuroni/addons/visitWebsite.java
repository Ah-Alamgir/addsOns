package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;

public class visitWebsite extends AppCompatActivity {

    visitwebsiteRecycler adapter;


    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_website);


        autoLoad.layoutId = R.layout.webpages;
        recyclerView = findViewById(R.id.webRecycle);
        recyclerView.setHasFixedSize(true);
        adapter = new visitwebsiteRecycler(this, autoLoad.webSites);
        Log.d("limks", String.valueOf(autoLoad.webSites));



        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);



        recyclerView.setAdapter(adapter);
    }
}