package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

        register.setOnClickListener(v -> {
            if (!name.getText().toString().isEmpty() && !phone.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                autoLoad.signup(name.getText().toString(), phone.getText().toString(), email.getText().toString(), password.getText().toString().trim(), this);
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