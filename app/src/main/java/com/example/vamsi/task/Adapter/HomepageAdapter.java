package com.example.vamsi.task.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vamsi.task.MainActivity;
import com.example.vamsi.task.MainActivity2;
import com.example.vamsi.task.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

/**
 * Created by Vamsi on 05-01-2018.
 */

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.CustomViewHolder> {

    Context context;
    LayoutInflater inflater;
    JSONArray repos;

    public HomepageAdapter(Context context, JSONArray repos) {
        this.context=context;
        this.repos = repos;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public HomepageAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v =inflater.inflate(R.layout.homedetail,parent,false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomepageAdapter.CustomViewHolder holder, final int position) {


        try {
            JSONObject currestRepo = repos.getJSONObject(position);
            JSONObject owner=currestRepo.getJSONObject("owner");
            holder.name.setText(currestRepo.getString("name"));
            holder.data.setText(currestRepo.getString("full_name"));
            holder.eye.setText(currestRepo.getString("watchers_count"));
            holder.star.setText(currestRepo.getString("stargazers_count"));
            holder.link.setText(currestRepo.getString("forks"));
            Picasso.with(context).load(owner.getString("avatar_url")).into(holder.image);
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context, MainActivity2.class);
                    try {
                        i.putExtra("currentrep",repos.getJSONObject(position).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    context.startActivity(i);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {

        if(repos.length()>10)
        return 10;
        else
        {
     return repos.length();
        }
    }

    public void dataChanged(JSONArray items) {
        repos = items;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name,data,eye,link,star;
        CardView cardView;

        public CustomViewHolder(View itemView) {
            super(itemView);

            cardView=(CardView)itemView.findViewById(R.id.card);
            image=(ImageView)itemView.findViewById(R.id.iconimage);
            name=(TextView)itemView.findViewById(R.id.name);
            data=(TextView)itemView.findViewById(R.id.data);
            eye=(TextView)itemView.findViewById(R.id.eye);
            link=(TextView)itemView.findViewById(R.id.link);
            star=(TextView)itemView.findViewById(R.id.star);

        }
    }
}
