package com.example.vamsi.task.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public CustomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
