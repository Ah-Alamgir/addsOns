package com.waskuroni.addons;

import static com.waskuroni.addons.autoLoad.getPoints;
import static com.waskuroni.addons.autoLoad.points;
import static com.waskuroni.addons.autoLoad.userName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class refetence extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refetence);


        EditText code = findViewById(R.id.referCode);
        Button continues= findViewById(R.id.done);

        continues.setOnClickListener(v -> {
            if (code.getText().toString().isEmpty()) {
                autoLoad.alart(this, "Plese enter a reference code");
            } else {
                getPoints(code.getText().toString());
            }
        });

    }














    public int ponts = 0;
    public void getPoints(String tags) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userPoints");

        myRef.child(tags).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                ponts = Integer.valueOf(task.getResult().getValue(Integer.class));
            }
            if(ponts>0) {
                autoLoad.points = ponts + autoLoad.points;
                // giving points who is the author of the refer code
                autoLoad.savePoints(userName, ponts+100);

                // Giving points to the person who inouted the code
                autoLoad.savePoints(autoLoad.userName,autoLoad.points);
            }else {
                autoLoad.alart(this, "Invalid refer code");
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,homes.class));
    }
}