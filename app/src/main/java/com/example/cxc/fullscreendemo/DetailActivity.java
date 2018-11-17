package com.example.cxc.fullscreendemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    private static final String TAG_DETAIL_FRAGMENT = "detailFragmentTag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        //获取参数
        /*//方式一：逐一获取，并传给Fragment
        String tUserName = intent == null ? "" : intent.getStringExtra(ExtraKeyConstants.NAME);
        int tUserAge = intent == null ? -1 : intent.getIntExtra(ExtraKeyConstants.AGE, -1);
        DetailFragment detailFragment = DetailFragment.newInstance(tUserName, tUserAge);
        Log.d(TAG, "-->onCreate()--tUserName:" + tUserName);
        Log.d(TAG, "-->tUserAge()--tUserAge:" + tUserName);*/

        //方式二：不获取，仅透传给Fragment
        //如果所传入的数据对当前Activity无用，直接透传即可
        Bundle bundle = intent == null ? null : intent.getExtras();
        DetailFragment detailFragment = DetailFragment.newInstance(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,
                detailFragment,
                TAG_DETAIL_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
