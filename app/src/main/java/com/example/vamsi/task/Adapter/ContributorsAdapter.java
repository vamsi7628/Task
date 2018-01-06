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

import com.example.vamsi.task.MainActivity;
import com.example.vamsi.task.MainActivity2;
import com.example.vamsi.task.R;
import com.example.vamsi.task.ScrollingActivity;

import java.util.concurrent.TimeoutException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Vamsi on 06-01-2018.
 */

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.CustomViewAdapter> {

    Context context;
    LayoutInflater inflater;
    MainActivity2 activityClass;
    public ContributorsAdapter(MainActivity2 activityClass) {
        this.activityClass=activityClass;
    }


    @Override
    public ContributorsAdapter.CustomViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = activityClass.getLayoutInflater().inflate(R.layout.contributorsdetail, null);
        return new CustomViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(ContributorsAdapter.CustomViewAdapter holder, int position) {

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(activityClass, ScrollingActivity.class);
                activityClass.startActivity(j);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
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
