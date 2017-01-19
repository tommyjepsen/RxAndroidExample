package com.tommyjepsen.rxandroidexample.Api;

import com.tommyjepsen.rxandroidexample.Api.Models.Post;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tommyjepsen on 19/01/2017.
 */

public interface ApiInterface {

    @GET("/posts/{id}")
    Observable<Post> getPost(@Path("id") int id);

    @GET("/posts")
    Observable<ArrayList<Post>> getPosts();

}
