package com.example.vamsi.task.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vamsi.task.MainActivity;
import com.example.vamsi.task.MainActivity2;
import com.example.vamsi.task.R;

import java.util.zip.Inflater;

/**
 * Created by Vamsi on 05-01-2018.
 */

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.CustomViewHolder> {

    Context context;
    LayoutInflater inflater;

    public HomepageAdapter(Context context) {
        this.context=context;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public HomepageAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v =inflater.inflate(R.layout.homedetail,parent,false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomepageAdapter.CustomViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, MainActivity2.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

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
