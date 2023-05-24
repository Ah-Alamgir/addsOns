package com.waskuroni.addons;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Spinner extends AppCompatActivity {
    Button btnSpin;
    ImageView ivWheel;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        // initializing views
        btnSpin = findViewById(R.id.Spin);
        ivWheel = findViewById(R.id.ivWheel);

        // creating an object of Random class

        Random random = new Random();

        // on click listener for btnSpin
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSpin.setEnabled(false);

                // reading random value between 10 to 30
                int spin = random.nextInt(20)+10;
                Log.d("spins", String.valueOf(spin));

                spin = spin * 36;

                // timer for each degree movement
                timer = new CountDownTimer(spin*8,1) {
                    @Override
                    public void onTick(long l) {
                        // rotate the wheel
                        float rotation = ivWheel.getRotation()+1;
                        ivWheel.setRotation(rotation);
                    }

                    @Override
                    public void onFinish() {
                        // enabling the button again
                        btnSpin.setEnabled(true);
                        Toast.makeText(getBaseContext(), "You Won !!" , Toast.LENGTH_SHORT ).show();
                    }
                }.start();

            }
        });

    }
}
