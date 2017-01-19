package com.tommyjepsen.rxandroidexample.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tommyjepsen on 19/01/2017.
 */
public class Api {

    private static Api ourInstance = new Api();

    public static final String API_BASEURL = "https://jsonplaceholder.typicode.com";

    Retrofit retrofit;
    ApiInterface apiInterface;

    public static Api getInstance() {
        return ourInstance;
    }

    private Api() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_BASEURL)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    public void setApiInterface(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }
}
