package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ViewFlipper;


public class splash extends AppCompatActivity {
    int click = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ViewFlipper viewFlipper = findViewById(R.id.flip);
        startActivity(new Intent(this, MainActivity.class));
        ImageButton nexts = findViewById(R.id.next);
        nexts.setOnClickListener(v -> {
            click= click+1;
            if(click==3){
                startActivity(new Intent(this, MainActivity.class));

            }else {
                viewFlipper.showNext();
            }


        });


    }
}