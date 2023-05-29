package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    Button editButton;
    TextView setName,phoneNumber, emailAddress;

    ImageButton youtube, Instagram;

    String youtubeUrl, instaUrl;


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
        Instagram = findViewById(R.id.insta);
        ImageButton back = findViewById(R.id.profileback);

        youtube.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
            startActivity(intent);
        });

        Instagram.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instaUrl));
            startActivity(intent);
        });
        back.setOnClickListener(v -> {
            finish();
        });
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
                youtubeUrl = value.getString("youtube");
                instaUrl = value.getString("instagram");
            }catch(Exception e) {
                Log.d("users", e.getMessage());
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });

       
    }




}