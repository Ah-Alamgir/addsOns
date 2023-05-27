package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSqliteDatabase();
        editButton.setOnClickListener(v -> startActivity(new Intent(this, editProfileDetails.class)));
    }



    public void getSqliteDatabase(){
        StringBuilder builder = new StringBuilder();
        autoLoad.sqlDatabase sqlDatabase = new autoLoad.sqlDatabase(profile.this);
        SQLiteDatabase database = sqlDatabase.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT NAME, EMAIL, PASSWORD, PHONE FROM PRODUCT", new String[]{});
        if (cursor != null){
            cursor.moveToFirst();
            String name = cursor.getString(0);
            String email = cursor.getString(1);
            String password = cursor.getString(2);
            String phone = cursor.getString(3);
            builder.append(name).append(email).append(password).append(phone);
        }


    }
}