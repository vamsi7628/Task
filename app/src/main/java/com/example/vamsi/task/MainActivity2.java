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
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.vamsi.task.Adapter.ContributorsAdapter;
import com.example.vamsi.task.Class.Contributors;
import com.example.vamsi.task.Class.mySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    CircleImageView user_image;
    ContributorsAdapter contributorsAdapter;
    JSONObject currentrepo;
    TextView project_link,description;
    String url_contributors;
    JSONObject contributor;
    List<Contributors>data=new ArrayList<>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        user_image=(CircleImageView)findViewById(R.id.iamge_icon);
        project_link=(TextView)findViewById(R.id.project_link);
        description=(TextView)findViewById(R.id.description);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_contribors);


        String name=getIntent().getStringExtra("currentrep");

        try {
            currentrepo=new JSONObject(name);
            JSONObject owner=currentrepo.getJSONObject("owner");
            url_contributors=currentrepo.getString("contributors_url");
            String current_username=currentrepo.getString("name");
            getSupportActionBar().setTitle(current_username);
            project_link.setText(currentrepo.getString("html_url"));
            description.setText(currentrepo.getString("description"));
            Picasso.with(this).load(owner.getString("avatar_url")).into(user_image);

            Log.i("Contributor",url_contributors);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url_contributors, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        Contributors con=new Contributors();
                        JSONObject jsonObject=response.getJSONObject(i);
                        con.setName(jsonObject.getString("login"));
                        con.setImage(jsonObject.getString("avatar_url"));
                        con.setRepo(jsonObject.getString("repos_url"));
                        data.add(con);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    contributorsAdapter=new ContributorsAdapter(MainActivity2.this,data);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity2.this, 3);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(contributorsAdapter);
                }



            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mySingleton.getInstance(MainActivity2.this).addToRequestQueue(jsonArrayRequest);



            project_link.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
            Intent i=new Intent(MainActivity2.this,Webview.class);
            i.putExtra("link", currentrepo.toString());
            startActivity(i);
    }
});

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
}
