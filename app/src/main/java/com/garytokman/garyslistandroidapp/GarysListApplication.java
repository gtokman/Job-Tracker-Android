package com.garytokman.garyslistandroidapp;
// Gary Tokman
// 11/22/16
// GaryslistAndroidApp

import com.google.firebase.database.FirebaseDatabase;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import timber.log.Timber;

public class GarysListApplication extends Application {

    private static GarysListApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        /*Persists data if no network*/
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.i("Created Garys List app!");
        sApplication = this;
    }

    public static GarysListApplication getApplication() {
        return sApplication;
    }

    public boolean hasNetwork() {
        return sApplication.checkNetwork();
    }

    private boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
