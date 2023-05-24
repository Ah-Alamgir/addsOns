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

        register.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show();
            } else if (email.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
            } else if (password.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter a password", Toast.LENGTH_SHORT).show();

            }else {
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



    public void save(String userName){
        autoLoad.userName = userName;
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", userName);
        editor.apply();

    }

}