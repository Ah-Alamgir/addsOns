package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    EditText name, email, password, phone;
    Button register, googleSign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);


        register = findViewById(R.id.register);
        googleSign = findViewById(R.id.googleSign);


        register.setOnClickListener(v -> {
            if (!name.getText().toString().isEmpty() && !phone.getText().toString().isEmpty() && !email.getText().toString().isEmpty && !password.getText().toString().isEmpty) {
                autoLoad.signup(name.getText(), phone.getText(), email.getText().trim(), password.getText().trim(), this);
            }
        });

        googleSign.setOnClickListener(v -> {
            finish();
        });
    }
}