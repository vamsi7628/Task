package com.example.vamsi.task.Adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vamsi.task.MainActivity2;
import com.example.vamsi.task.R;
import com.example.vamsi.task.ScrollingActivity;

import java.util.zip.Inflater;

/**
 * Created by Vamsi on 06-01-2018.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.CustomViewHolder> {
   ScrollingActivity scrollingActivity;
   LayoutInflater inflater;

    public RepositoryAdapter(ScrollingActivity scrollingActivity) {
        this.scrollingActivity=scrollingActivity;
        inflater= LayoutInflater.from(scrollingActivity);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =inflater.inflate(R.layout.homedetail,parent,false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(scrollingActivity, MainActivity2.class);
                scrollingActivity.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
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
