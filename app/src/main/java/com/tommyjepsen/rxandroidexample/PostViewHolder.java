package com.tommyjepsen.rxandroidexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tommyjepsen on 19/01/2017.
 */
public class PostViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.item_post_title_tv)
    public TextView itemPostTitleTv;
    @Bind(R.id.item_post_body_tv)
    public TextView itemPostBodyTv;

    public PostViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}