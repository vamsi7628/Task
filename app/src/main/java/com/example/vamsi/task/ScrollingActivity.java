package com.example.vamsi.task;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.vamsi.task.Adapter.RepositoryAdapter;

public class ScrollingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RepositoryAdapter repositoryAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.repository);
        repositoryAdapter=new RepositoryAdapter(ScrollingActivity.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ScrollingActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(repositoryAdapter);
    }
}
