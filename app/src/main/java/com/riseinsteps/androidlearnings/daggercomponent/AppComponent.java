package com.riseinsteps.androidlearnings.daggercomponent;

import com.riseinsteps.androidlearnings.DaggerActivity;
import com.riseinsteps.androidlearnings.module.AppModule;
import com.riseinsteps.androidlearnings.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void injectActivity(DaggerActivity daggerActivity);
}
