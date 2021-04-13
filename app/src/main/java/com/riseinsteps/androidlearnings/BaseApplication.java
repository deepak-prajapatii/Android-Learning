package com.riseinsteps.androidlearnings;

import android.app.Application;

import com.riseinsteps.androidlearnings.daggercomponent.AppComponent;
import com.riseinsteps.androidlearnings.daggercomponent.DaggerAppComponent;
import com.riseinsteps.androidlearnings.module.AppModule;
import com.riseinsteps.androidlearnings.module.NetworkModule;

public class BaseApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getNetworkAppComponent() {
        return appComponent;
    }
}
