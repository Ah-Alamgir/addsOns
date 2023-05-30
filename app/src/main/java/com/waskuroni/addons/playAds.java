package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class playAds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ads);
        TextView textView = findViewById(R.id.packName);
        textView.setText("Points::   " +String.valueOf(autoLoad.points));





        Button showAds = findViewById(R.id.tvPlayAd);
        showAds.setOnClickListener(view -> {
            if(autoLoad.addShowed <1){
                autoLoad.alart(this,"This task has already been completed");

            }else {
                int ammount =  autoLoad.showReward(this, this, "");
                autoLoad.points = autoLoad.points+ ammount;
                autoLoad.addShowed = autoLoad.addShowed-1;
                textView.setText("Points::   " +String.valueOf(autoLoad.points));
            }

        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,homes.class));
    }
}