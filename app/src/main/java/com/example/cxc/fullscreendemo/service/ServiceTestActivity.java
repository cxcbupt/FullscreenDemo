package com.example.cxc.fullscreendemo.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.cxc.fullscreendemo.R;

public class ServiceTestActivity extends AppCompatActivity {
    private static final String TAG = "ServiceTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service_test);

        Button getRandomBtn = findViewById(R.id.get_random_btn);
        getRandomBtn.setOnClickListener(v -> onGetRandomBtnClick());
    }


    @Override
    protected void onStart() {
        super.onStart();

        Intent bindServiceIntent = new Intent(this, HelloBoundService.class);
        bindService(bindServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();

        unbindService(mServiceConnection);
        mBound = false;
    }

    private void onGetRandomBtnClick() {
        Log.d(TAG, "-->onGetRandomBtnClick()--");
        if (mBound && mHelloBoundService != null) {
            int randomInt = mHelloBoundService.getRandomNumber();
            Log.d(TAG, "-->onGetRandomBtnClick()--randomInt:" + randomInt);
        }
    }


    private HelloBoundService mHelloBoundService = null;
    boolean mBound = false;

    /**
     * Defines callbacks for service binding, passed to bindService()
     * <p>
     * ServiceConnection, which monitors the connection with the service.
     * When the Android system creates the connection between the client and service,it calls onServiceConnected() on the ServiceConnection.
     * The onServiceConnected() method includes an IBinder argument, which the client then uses to communicate with the bound service.
     */
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        //The system calls this to deliver the IBinder returned by the service's onBind() method.
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "-->onServiceConnected()--name=" + name + ",service=" + service);
            if (service instanceof HelloBoundService.LocalBinder) {
                mHelloBoundService = ((HelloBoundService.LocalBinder) service).getService();
                mBound = true;
            }
        }

        //The Android system calls this when the connection to the service is unexpectedly lost,
        // such as when the service has crashed or has been killed. This is not called when the client unbinds.
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "-->onServiceDisconnected()--name=" + name);
            mHelloBoundService = null;
            mBound = false;
        }
    };
}
