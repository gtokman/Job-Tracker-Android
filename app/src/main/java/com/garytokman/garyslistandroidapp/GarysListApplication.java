package com.garytokman.garyslistandroidapp;
// Gary Tokman
// 11/22/16
// GaryslistAndroidApp

import android.app.Application;

import timber.log.Timber;

public class GarysListApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.i("Created Garys List app!");
    }
}
