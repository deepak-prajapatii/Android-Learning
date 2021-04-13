package com.riseinsteps.androidlearnings.rxjava;

import com.riseinsteps.androidlearnings.model.Model;

import retrofit2.http.GET;
import rx.Observable;

public interface RetrofitService {
    @GET("api/users?page=1/")
    Observable<Model> getModel();
}
