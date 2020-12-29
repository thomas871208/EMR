package com.james.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class EMRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){

            getSupportActionBar().hide();

        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_m_r);

        WebView webView = new WebView(this);
        setContentView(webView);
        webView.loadUrl("http://172.20.10.2/msc/EMRPHP/A01.php");


    }
}