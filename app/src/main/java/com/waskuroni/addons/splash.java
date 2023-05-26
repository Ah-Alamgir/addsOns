package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Objects;


public class splash extends AppCompatActivity {
    int click = 0;
    ImageButton nexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewFlipper viewFlipper = findViewById(R.id.flip);
        TextView description = findViewById(R.id.des);
        TextView header = findViewById(R.id.header);








        autoLoad.getdata("web");

        nexts = findViewById(R.id.next);
        nexts.setVisibility(View.INVISIBLE);



        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        String username = pref.getString("name", "@hanif");
        if (Objects.equals(username, "@hanif")){
            nexts.setVisibility(View.VISIBLE);
        }else {
            autoLoad.loadAdd(this);
            autoLoad.userName = username;
            Intent myIntent = new Intent(splash.this, homes.class);
            startActivity(myIntent);
            finish();
        }










        nexts.setOnClickListener(v -> {
            click= click+1;
            if(click==3){
                startActivity(new Intent(this, signIn.class));

            }else {
                viewFlipper.showNext();
                if (click ==1){
                    description.setText("Complete Your daily Task and get paid");
                    header.setText("Get paid");
                }else if (click==2){
                    description.setText("Use our app to get real money by doing task");
                    header.setText("Be luckey");
                }
            }


        });


    }





}