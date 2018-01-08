package com.example.vamsi.task;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ForwardingListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.vamsi.task.Adapter.RepositoryAdapter;
import com.example.vamsi.task.Class.Repositors;
import com.example.vamsi.task.Class.mySingleton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ScrollingActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RepositoryAdapter repositoryAdapter;
    CircleImageView circleImageView;
    ProgressDialog progressDialog;
    String repo_url;
    List<Repositors>items=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.repository);

        String name=getIntent().getStringExtra("name");
       String image=getIntent().getStringExtra("image");

        progressDialog=new ProgressDialog(ScrollingActivity.this);
        progressDialog.setMessage("its Loading");
        progressDialog.show();

        getSupportActionBar().setTitle(name);
        circleImageView=(CircleImageView)findViewById(R.id.image_repo);
        Picasso.with(this).load(image).into(circleImageView);

        repo_url=getIntent().getStringExtra("repo");
        Log.i("repos",repo_url);


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(repo_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("repos",repo_url);
                progressDialog.dismiss();
                JSONArray data=response;
                RepositoryAdapter.dataChanged(data);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mySingleton.getInstance(ScrollingActivity.this).addToRequestQueue(jsonArrayRequest);

        repositoryAdapter=new RepositoryAdapter(ScrollingActivity.this,new JSONArray());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ScrollingActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(repositoryAdapter);


    }





}
