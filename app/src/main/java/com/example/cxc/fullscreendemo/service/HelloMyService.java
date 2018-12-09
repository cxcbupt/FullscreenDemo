package com.example.cxc.fullscreendemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class HelloMyService extends Service {
    private static final String TAG = "HelloMyService";

    private final IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        Log.d(TAG, "-->onCreate()--");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "-->onStartCommand(Intent intent=" + intent
                + ", int flags=" + flags
                + ", int startId=" + startId + ")--");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "-->onDestroy()--");
        super.onDestroy();
    }

    public class LocalBinder extends Binder {
        public HelloMyService getService() {
            return HelloMyService.this;
        }
    }

    private final Random mRandom = new Random();

    public int getRandomNumber() {
        return mRandom.nextInt(100);
    }
}
