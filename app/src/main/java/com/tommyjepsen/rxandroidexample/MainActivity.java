package com.tommyjepsen.rxandroidexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tommyjepsen.rxandroidexample.Api.Api;
import com.tommyjepsen.rxandroidexample.Api.Models.Post;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    ArrayList<Post> posts = new ArrayList<>();

    @BindView(R.id.activity_main_rv)
    RecyclerView activityMainRv;
    PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        postAdapter = new PostAdapter(posts, new PostAdapter.OnPostListener() {
            @Override
            public void onPostPressed(int position) {
                Intent startPost = new Intent(MainActivity.this, PostActivity.class);
                startPost.putExtra(Constants.INTENT_POST_ID, posts.get(position).getId());
                startActivity(startPost);
            }
        });
        activityMainRv.setAdapter(postAdapter);
        activityMainRv.setLayoutManager(new LinearLayoutManager(this));

        Observable<ArrayList<Post>> postObservable = Api.getInstance().getApiInterface().getPosts();
        postObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Post>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onNext(ArrayList<Post> pList) {
                        posts.addAll(pList);
                        postAdapter.notifyDataSetChanged();
                    }
                });

    }
}
