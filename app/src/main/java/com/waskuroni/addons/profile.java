package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }



    public void getSqliteDatabase(){
        autoLoad.sqlDatabase sqlDatabase = new autoLoad.sqlDatabase(profile.this);
        SQLiteDatabase database = sqlDatabase.getReadableDatabase();
    }
}