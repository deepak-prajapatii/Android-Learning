package com.riseinsteps.androidlearnings.rxjava;

import com.riseinsteps.androidlearnings.model.Model;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class RetrofitClient {
    public static final String BASE_URL = "https://reqres.in/";
    public static RetrofitClient instance;
    public RetrofitService retrofitService;

    private RetrofitClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public Observable<Model> getModel() {
        return retrofitService.getModel();
    }
}
