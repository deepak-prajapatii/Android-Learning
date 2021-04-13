package com.riseinsteps.androidlearnings.daggercomponent;

import com.riseinsteps.androidlearnings.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DaggerApi {

    @GET("api/users?page=1/")
    Call<Model> getModel();
}
