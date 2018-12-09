package com.example.cxc.fullscreendemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * The IntentService class does the following:
 * <p>
 * It creates a default worker thread that executes all of the intents that are delivered to onStartCommand(), separate from your application's main thread.
 * Creates a work queue that passes one intent at a time to your onHandleIntent() implementation, so you never have to worry about multi-threading.
 * Stops the service after all of the start requests are handled, so you never have to call stopSelf().
 * Provides a default implementation of onBind() that returns null.
 * Provides a default implementation of onStartCommand() that sends the intent to the work queue and then to your onHandleIntent() implementation.
 */

/**
 * That's all you need: a constructor and an implementation of onHandleIntent().
 * <p>
 * If you decide to also override other callback methods,
 * such as onCreate(), onStartCommand(), or onDestroy(),
 * be sure to call the super implementation so that the IntentService can properly handle the life of the worker thread.
 */
public class HelloIntentService extends IntentService {
    private static final String TAG = "HelloIntentService";

    /**
     * A constructor is required, and must call the super <code>IntentService(String)</code>
     * constructor with a name for the worker thread.
     */
    public HelloIntentService() {
        super("HelloIntentService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "-->onHandleIntent(Intent intent=" + intent + ")--");

    }

}
