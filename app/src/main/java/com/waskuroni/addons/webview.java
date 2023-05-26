package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class webview extends AppCompatActivity {

    public static String link;
    public static String collectpoints;
    WebView webs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        webs =  findViewById(R.id.web);
        webs.loadUrl(link);
        Log.d("limks", link);

    }


    @Override
    public void onBackPressed() {
        if (webs.canGoBack()) {
            webs.goBack();
        }else {
            super.onBackPressed();
            finish();
        }

    }
}