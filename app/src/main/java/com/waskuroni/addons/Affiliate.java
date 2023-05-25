package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

public class Affiliate extends AppCompatActivity {
    instGameRecyclelar adapter;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiliate);


        autoLoad.layoutId = R.layout.webpages_affiliate;
        recyclerView = findViewById(R.id.affiliateRecycle);
        recyclerView.setHasFixedSize(true);
        adapter = new instGameRecyclelar(this, autoLoad.affiliates);


        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);



        recyclerView.setAdapter(adapter);
    }
}