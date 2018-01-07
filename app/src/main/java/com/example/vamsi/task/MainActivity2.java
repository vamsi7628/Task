package com.example.vamsi.task;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.MediaActionSound;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.test.mock.MockApplication;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.vamsi.task.Adapter.ContributorsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    ContributorsAdapter contributorsAdapter;
        JSONObject cureentrepo;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String name=getIntent().getStringExtra("currentrep");

        try {
            cureentrepo=new JSONObject(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String url_contributors=cureentrepo.getString("contributors_url");
            Log.i("Contributor",url_contributors);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        recyclerView=(RecyclerView)findViewById(R.id.recycler_contribors);
        contributorsAdapter=new ContributorsAdapter(MainActivity2.this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity2.this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(contributorsAdapter);


    }
}
