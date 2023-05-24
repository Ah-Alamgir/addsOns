package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class refetence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refetence);


        EditText code = findViewById(R.id.referCode);
        Button continues= findViewById(R.id.done);

        continues.setOnClickListener(v -> {
            if(code.getText().toString().isEmpty()) {
                Toast.makeText(this, "Plese enter a reference code", Toast.LENGTH_SHORT).show();
            }else {
                int ponts= autoLoad.getPoints(code.getText().toString());
                if(ponts>0) {
                    autoLoad.points = ponts + autoLoad.points;
                    autoLoad.savePoints(code.getText().toString(), ponts+100);
                }else {
                    Toast.makeText(this, "Invalid refer code", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}