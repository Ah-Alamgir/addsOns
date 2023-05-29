package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    Button editButton;
    TextView setName;
    EditText emailAddress;
    EditText phoneNumber;
    EditText youtube;
    EditText instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        editButton = findViewById(R.id.edit);
        getuserdetailsFromFirebase();
        editButton.setOnClickListener(v -> startActivity(new Intent(this, profileEdit.class)));

        setName = findViewById(R.id.naming);

        emailAddress = findViewById(R.id.ema);
        phoneNumber = findViewById(R.id.ph);
        youtube = findViewById(R.id.youtu);
        instagram = findViewById(R.id.insta);
    }






    public void getuserdetailsFromFirebase(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(firebaseAuth.getCurrentUser().getUid());
        docRef.addSnapshotListener(this, (value, error) -> {
            try {
                setName.setText(value.getString("name"));
                emailAddress.setText(value.getString("email"));
                phoneNumber.setText(value.getString("phone"));
                youtube.setText(value.getString("youtube"));
                instagram.setText(value.getString("instagram"));
            }catch(Exception e) {
                Log.d("users", e.getMessage());
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

       
    }




}