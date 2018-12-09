package com.example.cxc.fullscreendemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.cxc.fullscreendemo.apk.PackageUtils;
import com.example.cxc.fullscreendemo.decoration.RecyclerViewTestActivity;
import com.example.cxc.fullscreendemo.notification.NotificationUtils;
import com.example.cxc.fullscreendemo.service.HelloMyService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //打开详情
        View detailBtn = findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(v -> onDetailBtnClick());

        //打开RecyclerView
        View recyclerBtn = findViewById(R.id.recycler_view_btn);
        recyclerBtn.setOnClickListener(v -> onTestRecyclerViewBtnClick());

        //Launch Notification
        View launchNotificationBtn = findViewById(R.id.launch_notification_btn);
        launchNotificationBtn.setOnClickListener(v -> onLaunchNotificationBtnClick());

        //安装Apk
        View installApkBtn = findViewById(R.id.install_apk_btn);
        installApkBtn.setOnClickListener(v -> onInstallApkBtnClick());

        //Query Intent
        View queryIntentBtn = findViewById(R.id.query_intent_btn);
        queryIntentBtn.setOnClickListener(v -> onQueryIntentBtnClick());

        //隐式Intent启动Activity
        View startActivityBtn = findViewById(R.id.start_activity_btn);
        startActivityBtn.setOnClickListener(v -> onStartActivityBtnClick());

        //startService
        View startServiceBtn = findViewById(R.id.start_service_btn);
        startServiceBtn.setOnClickListener(v -> onStartServiceBtnClick());
    }

    private void onDetailBtnClick() {
        Intent intent = new Intent(this, DetailActivity.class);
        //传参
        intent.putExtra(ExtraKeyConstants.NAME, "CXC");
        intent.putExtra(ExtraKeyConstants.AGE, 22);

        /*//传参
        Bundle extras = new Bundle();
        extras.putString(ExtraKeyConstants.NAME, "CXC");
        extras.putInt(ExtraKeyConstants.AGE, 22);
        intent.putExtras(extras);*/
        startActivity(intent);
    }

    private void onTestRecyclerViewBtnClick() {
        Intent intent = new Intent(this, RecyclerViewTestActivity.class);
        startActivity(intent);
    }

    public static final int NOTIFICATION_ID = 888;

    private void onLaunchNotificationBtnClick() {
        String textTitle = "This is Title";
        String textContent = "This is Content";

        //创建Notification Channel
        String channelId = NotificationUtils.createNotificationChannel(getApplicationContext());

        //创建Notification并与Channel关联
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //Launch Notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }


    private void onInstallApkBtnClick() {
//        String localAPKPath = PackageUtils.getFilePath(getApplicationContext());
        String localAPKPath = "/data/data/com.example.cxc.fullscreendemo/cache/fill_screen_1.0.2.apk";
        Log.d(TAG, "-->onInstallApkBtnClick()--localAPKPath=" + localAPKPath);
        PackageUtils.onApkDownLoadCompleted(getApplicationContext(), localAPKPath);
        PackageUtils.onInstallApkBtnClick(this, localAPKPath);
    }

    private void onQueryIntentBtnClick() {
        Log.d(TAG, "-->onQueryIntentBtnClick()--");
        Intent testAIntent = new Intent();
        testAIntent.setAction("com.example.cxc.intentFilter.testA");
//        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(testAIntent, PackageManager.MATCH_DEFAULT_ONLY);
        List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(testAIntent, PackageManager.MATCH_ALL);
        if (resolveInfoList != null) {
            for (ResolveInfo info : resolveInfoList) {
                Log.d(TAG, "-->info:" + info);
            }
        }
    }

    private void onStartActivityBtnClick() {
        Log.d(TAG, "-->onStartActivityBtnClick()--");
        Intent testAIntent = new Intent();
        testAIntent.setAction("com.example.cxc.intentFilter.testA");
        if (getPackageManager().resolveActivity(testAIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            startActivity(testAIntent);
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

    private void onStartServiceBtnClick() {
        Log.d(TAG, "-->onStartActivityBtnClick()--");
        if (mBound && mHelloMyService != null) {
            int randomInt = mHelloMyService.getRandomNumber();
            Log.d(TAG, "-->onStartServiceBtnClick()--randomInt:" + randomInt);
        }
    }
}
