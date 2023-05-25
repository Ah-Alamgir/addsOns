package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class withdraw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);


        RadioButton bkash = findViewById(R.id.blash);
        RadioButton nagad = findViewById(R.id.nagad);
        Button submit = findViewById(R.id.submit);
        TextView yourPoint = findViewById(R.id.yourPoints);
        EditText withPoints = findViewById(R.id.withPoins);
        EditText withNumners = findViewById(R.id.withnumber);

        yourPoint.setText("Your points is :   "+ autoLoad.points);


        submit.setOnClickListener(v -> {
            if(autoLoad.points<1000){
                autoLoad.alart(this, "You must earn atlest 1000 points");
            }else {
                if(withPoints.getText().toString().isEmpty()) {
                    autoLoad.alart(this,"Please enter the amount you wnat to withdraw");
                } else if (withNumners.getText().toString().isEmpty()) {
                    autoLoad.alart(this, "Plese enter your Nagad or Bkash number");
                } else if (bkash.isChecked() && Integer.valueOf(withPoints.getText().toString())>999 && !withNumners.getText().toString().isEmpty()) {
                    autoLoad.withDraw(withPoints.getText().toString() + "   number:" + withNumners.getText().toString() + "bkash");
                    autoLoad.alart(this,  "Your points will be deducted while paying you");

                } else if (nagad.isChecked() && Integer.valueOf(withPoints.getText().toString())>999 && !withNumners.getText().toString().isEmpty()) {
                    autoLoad.withDraw(withPoints.getText().toString() + "   number:" + withNumners.getText().toString() + "Nagad");
                    autoLoad.alart(this,  "Your points will be deducted while paying you");
                }else {
                    autoLoad.alart(this, "Please fill the required field");
                }

            }


        });

    }
}