package com.example.vamsi.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

public class Webview extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webview=(WebView)findViewById(R.id.webview);

        String web=getIntent().getStringExtra("link");

        try {
            JSONObject webitem=new JSONObject(web);

            webview.loadUrl(webitem.getString("html_url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
