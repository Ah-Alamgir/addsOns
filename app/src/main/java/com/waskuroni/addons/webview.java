package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class webview extends AppCompatActivity {

    public static String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        WebView webView = (WebView) findViewById(R.id.web);
        webView.loadUrl(link);

    }
}