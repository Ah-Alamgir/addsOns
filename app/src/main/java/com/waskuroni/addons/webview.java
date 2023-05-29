package com.waskuroni.addons;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class webview extends AppCompatActivity {

    public static String link;
    boolean timeRemained = true;
    public static String collectpoints;
    TextView visitPoints, timeRemaining;
    WebView webs;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        webs = findViewById(R.id.web);
        visitPoints = findViewById(R.id.visitPoints);
        visitPoints.setText(String.valueOf("  Your points:"+autoLoad.points));
        timeRemaining = findViewById(R.id.visitTitle);
        ImageButton visitbacks= findViewById(R.id.visitBack);


        startTimer();


        progressBar = findViewById(R.id.progressBars);
        webs.getSettings().setJavaScriptEnabled(true);
        webs.getSettings().setAllowFileAccess(true);
        webs.loadUrl(link);

        visitbacks.setOnClickListener(v -> {
            finish();
        });


        webs.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }


        });


        webs.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (webs.canGoBack()) {
            webs.goBack();
        } else {
            super.onBackPressed();
            finish();
        }

    }

    CountDownTimer cTimer = null;

    //start timer function
    void startTimer() {
        cTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeRemaining.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                autoLoad.points = autoLoad.points+ Integer.valueOf(collectpoints);
                autoLoad.savePoints(autoLoad.userName,autoLoad.points);
                visitPoints.setText(String.valueOf("  Your points:"+ autoLoad.points));
                timeRemained = false;
            }
        };
        cTimer.start();
    }


    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }
}