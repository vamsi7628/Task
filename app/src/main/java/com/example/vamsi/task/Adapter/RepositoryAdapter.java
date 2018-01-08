package com.example.vamsi.task.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vamsi.task.Class.Contributors;
import com.example.vamsi.task.MainActivity2;
import com.example.vamsi.task.R;
import com.example.vamsi.task.ScrollingActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

/**
 * Created by Vamsi on 06-01-2018.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.CustomViewHolder> {
   Context context;
   LayoutInflater inflater;
    static JSONArray object;

    public RepositoryAdapter(Context context,   JSONArray object) {
        this.context=context;
        this.object=object;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =inflater.inflate(R.layout.homedetail,parent,false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        try {
            JSONObject currestRepo = object.getJSONObject(position);
            Log.i("dd",currestRepo.toString());
            final JSONObject owner=currestRepo.getJSONObject("owner");
            holder.name.setText(currestRepo.getString("name"));
            holder.data.setText(currestRepo.getString("full_name"));
            holder.eye.setText(currestRepo.getString("watchers_count"));
            holder.star.setText(currestRepo.getString("stargazers_count"));
            holder.link.setText(currestRepo.getString("forks"));

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(context, MainActivity2.class);
                    try {
                        i.putExtra("currentrep",owner.getJSONObject(String.valueOf(position)).toString());
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
        if(object.length()>10)
            return 10;
        else
        {
            return object.length();
        }
    }


    public static void dataChanged(JSONArray jsonArray) {
    object=jsonArray;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,data,eye,link,star;
        CardView cardView;
        public CustomViewHolder(View itemView) {
            super(itemView);

            cardView=(CardView)itemView.findViewById(R.id.card);
            image=(ImageView)itemView.findViewById(R.id.image);
            name=(TextView)itemView.findViewById(R.id.name);
            data=(TextView)itemView.findViewById(R.id.data);
            eye=(TextView)itemView.findViewById(R.id.eye);
            link=(TextView)itemView.findViewById(R.id.link);
            star=(TextView)itemView.findViewById(R.id.star);
        }
    }
}
