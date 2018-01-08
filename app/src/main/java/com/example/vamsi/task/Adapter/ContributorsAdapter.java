package com.example.vamsi.task.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vamsi.task.Class.Contributors;
import com.example.vamsi.task.MainActivity;
import com.example.vamsi.task.MainActivity2;
import com.example.vamsi.task.R;
import com.example.vamsi.task.ScrollingActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeoutException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Vamsi on 06-01-2018.
 */

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.CustomViewAdapter> {

    Context context;
    LayoutInflater inflater;
    MainActivity2 activityClass;
    List<Contributors> data;

    public ContributorsAdapter(Context context, List<Contributors> data) {
    this.context=context;
    this.data=data;
        inflater= LayoutInflater.from(context);
    }


    @Override
    public ContributorsAdapter.CustomViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =inflater.inflate(R.layout.contributorsdetail, null);
        return new CustomViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(ContributorsAdapter.CustomViewAdapter holder, final int position) {

        final Contributors items=data.get(position);
        holder.name.setText(items.getName());
        Picasso.with(context).load(items.getImage()).into(holder.circleImageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(context, ScrollingActivity.class);
                j.putExtra("name" ,data.get(position).getName() );
                j.putExtra("image" ,data.get(position).getImage() );
                j.putExtra("repo" ,data.get(position).getRepo() );
                context.startActivity(j);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class CustomViewAdapter extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView name;
        CircleImageView circleImageView;
        public CustomViewAdapter(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linear);
            circleImageView=(CircleImageView)itemView.findViewById(R.id.thumbnail);
            name=(TextView)itemView.findViewById(R.id.name_contributor);
        }
    }
}
