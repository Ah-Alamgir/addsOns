package com.waskuroni.addons;


import android.app.admin.DeviceAdminService;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Spinner extends AppCompatActivity {
    CountDownTimer timer;

    final int[] sector = {25,5,15,5,15,5,25,15,5,100,5,15};
    final int[] sectorDegrees = new int[sector.length];
    int randomSectorIndex = 0;

    ImageView ivWheel;
    Button btnSpin;


    boolean spinning = false;
    int earningRecord = 0;
    Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // initializing views
        btnSpin = findViewById(R.id.Spin);
        ivWheel = findViewById(R.id.ivWheel);
        TextView spinPOints = findViewById(R.id.textTitle);
        spinPOints.setText(String.valueOf(autoLoad.points));

        generateSectorDegres();












        ImageButton backs = findViewById(R.id.spineback);
        backs.setOnClickListener(v -> finish());

        btnSpin.setOnClickListener(view -> {

            if(!spinning){
                spinning = true;
                randomSectorIndex  = random.nextInt(sector.length);
                int randomDegree =  generateRandomDegreetoSpin();
                RotateAnimation rotateAnimation = new RotateAnimation(0, randomDegree,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());

                rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int earnedCoin = sector[sector.length-(randomSectorIndex+1)];
                        spinning= false;
                        autoLoad.points =earnedCoin+ autoLoad.points;
                        autoLoad.savePoints(autoLoad.userName, autoLoad.points);
                        Toast.makeText(Spinner.this, String.valueOf(earnedCoin), Toast.LENGTH_LONG).show();
                        spinPOints.setText(String.valueOf(autoLoad.points));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                ivWheel.startAnimation(rotateAnimation);
            }

        });

    }

    private int generateRandomDegreetoSpin() {
        return (360*sector.length)+ sectorDegrees[randomSectorIndex];
    }


    public void generateSectorDegres(){
        int sectorDegreee = 360/sector.length;
        for(int i = 0; i < sector.length; i++){
            sectorDegrees[i] = sectorDegreee*(i+1);
        }
    }
}
