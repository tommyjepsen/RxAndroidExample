package com.tommyjepsen.rxandroidexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tommyjepsen.rxandroidexample.Api.Models.Post;

import java.util.ArrayList;

/**
 * Created by tommyjepsen on 19/01/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    ArrayList<Post> posts = new ArrayList<>();
    OnPostListener onPostListener;

    public PostAdapter(ArrayList<Post> posts, OnPostListener onPostListener) {
        this.posts = posts;
        this.onPostListener = onPostListener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        PostViewHolder vh = new PostViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, final int position) {
        holder.itemPostTitleTv.setText(posts.get(position).getTitle());
        holder.itemPostBodyTv.setText(posts.get(position).getBody());
        holder.itemPostTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostListener.onPostPressed(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public interface OnPostListener {
        public void onPostPressed(int position);
    }
}
