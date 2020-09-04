package com.firebase.demo1;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

public class MyApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        /*FirebaseOptions options = new FirebaseOptions.Builder()
                .setApiKey("ba622b1c67f4d02eb6d483b2493da0424ba39e41")
                .setApplicationId("1:516189320290:android:209d38da9b0ead62de0d3b")
                .setProjectId("fir-12349")
                .setDatabaseUrl("https://fir-12349.firebaseio.com")
                .build();*/
        FirebaseApp.initializeApp(this);
    }
}
