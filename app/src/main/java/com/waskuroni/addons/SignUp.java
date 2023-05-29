package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText name, email, password, phone;
    Button register, googleSign, logPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.names);
        email = findViewById(R.id.emails);
        password = findViewById(R.id.passwords);
        phone = findViewById(R.id.phones);


        register = findViewById(R.id.registers);
        googleSign = findViewById(R.id.googleSigns);
        logPage=  findViewById(R.id.logspage);


        String named= name.getText().toString().trim();
        String emailed= email.getText().toString().trim();
        String passworded= password.getText().toString().trim();
        String phoned= phone.getText().toString().trim();

        register.setOnClickListener(v -> {
            if (named.isEmpty()){
                autoLoad.alart(this, "Enter your name");
            } else if (emailed.isEmpty()) {
                autoLoad.alart(this, "Enter your email");
            } else if (phoned.isEmpty()) {
                autoLoad.alart(this, "Enter your phone");
            } else if (passworded.isEmpty()) {
                autoLoad.alart(this, "Enter your password");

            }else {
                ProgressDialog dialog = ProgressDialog.show(this, "Registering",
                        "Loading. Please wait...", true);
                String returned = autoLoad.signup(named,phoned,emailed,passworded);
                if( returned == "success"){
                    dialog.dismiss();
                    startActivity(new Intent(SignUp.this, homes.class));
                    finish();
                }else {
                    dialog.dismiss();
                    autoLoad.alart(this, returned);
                }
            }
        });















        googleSign.setOnClickListener(v -> {
            finish();
        });


        logPage.setOnClickListener(v -> {
            startActivity(new Intent(SignUp.this, signIn.class));
        });
    }





}