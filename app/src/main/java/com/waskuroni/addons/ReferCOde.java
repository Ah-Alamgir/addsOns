package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ReferCOde extends AppCompatActivity {

    TextView referCode;
    Button copied;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_code);

        referCode = findViewById(R.id.textView_referenceCode);
        referCode.setText(autoLoad.userName);

        copied=  findViewById(R.id.copy);
        copied.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", autoLoad.userName);
            clipboard.setPrimaryClip(clip);
        });

    }
}