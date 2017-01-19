package com.tommyjepsen.rxandroidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.tommyjepsen.rxandroidexample.Api.Api;
import com.tommyjepsen.rxandroidexample.Api.Models.Post;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PostActivity extends AppCompatActivity {

    public static final String TAG = PostActivity.class.getName();

    @Bind(R.id.item_post_title_tv)
    TextView itemPostTitleTv;
    @Bind(R.id.item_post_body_tv)
    TextView itemPostBodyTv;

    int postId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_post);
        ButterKnife.bind(this);

        postId = getIntent().getExtras().getInt(Constants.INTENT_POST_ID);

        Observable<Post> postObservable = Api.getInstance().getApiInterface().getPost(postId);
        postObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(Post post) {
                        itemPostTitleTv.setText(post.getTitle());
                        itemPostBodyTv.setText(post.getBody());
                    }
                });
    }
}
