package com.example.cxc.fullscreendemo.intentFilter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.cxc.fullscreendemo.R;

public class IntentFilterATestActivity extends AppCompatActivity {
    private static final String TAG = "IntentFilterATestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter_a_test);
    }
}
