package com.riseinsteps.androidlearnings.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    public static final String BASE_URL = "https://reqres.in/";



    @Provides
    @Singleton
    Gson providesGSon() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(providesGSon()))
                .build();

        return retrofit;
    }

}
