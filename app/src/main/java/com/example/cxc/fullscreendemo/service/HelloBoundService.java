package com.example.cxc.fullscreendemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * 一个可绑定Service的简单实现(Extending the Binder class)
 * 适用于：service is private to your own application and runs in the same process as the client (which is common)
 * <p>
 * Here's how to set it up:
 * <p>
 * 1.In your service, create an instance of Binder that does one of the following:
 * (1-1)Contains public methods that the client can call.
 * (1-2)Returns the current Service instance, which has public methods the client can call.
 * (1-3)Returns an instance of another class hosted by the service with public methods the client can call.
 * 2.Return this instance of Binder from the onBind() callback method.
 * 3.In the client, receive the Binder from the onServiceConnected() callback method and make calls to the bound service using the methods provided.
 */
public class HelloBoundService extends Service {
    private static final String TAG = "HelloBoundService";

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    // Random number generator
    private final Random mRandom = new Random();

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

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public HelloBoundService getService() {
            return HelloBoundService.this;
        }
    }

    public int getRandomNumber() {
        return mRandom.nextInt(100);
    }
}
