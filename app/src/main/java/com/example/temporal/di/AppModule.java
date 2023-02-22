package com.example.temporal.di;

import android.app.Application;

import com.example.temporal.DialogManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    public AppModule() {}

    @Singleton
    @Provides
    public AppNavigator getAppNavigator() {
        return new AppNavigatorImpl();
    }
}
