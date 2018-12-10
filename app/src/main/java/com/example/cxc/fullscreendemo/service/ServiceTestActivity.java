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

        Intent bindServiceIntent = new Intent(this, HelloMyService.class);
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
        if (mBound && mHelloMyService != null) {
            int randomInt = mHelloMyService.getRandomNumber();
            Log.d(TAG, "-->onGetRandomBtnClick()--randomInt:" + randomInt);
        }
    }


    private HelloMyService mHelloMyService = null;
    boolean mBound = false;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        //The system calls this to deliver the IBinder returned by the service's onBind() method.
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "-->onServiceConnected()--name=" + name + ",service=" + service);
            if (service instanceof HelloMyService.LocalBinder) {
                mHelloMyService = ((HelloMyService.LocalBinder) service).getService();
                mBound = true;
            }
        }

        //The Android system calls this when the connection to the service is unexpectedly lost,
        // such as when the service has crashed or has been killed. This is not called when the client unbinds.
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "-->onServiceDisconnected()--name=" + name);
            mHelloMyService = null;
            mBound = false;
        }
    };
}
