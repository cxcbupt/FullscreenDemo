package com.example.cxc.fullscreendemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";

    private TextView detailTv;

    private String userName;
    private int userAge;

    public static DetailFragment newInstance(String userName, int userAge) {
        Bundle args = new Bundle();
        args.putString(ExtraKeyConstans.NAME, userName);
        args.putInt(ExtraKeyConstans.AGE, userAge);
        Log.d(TAG, "-->newInstance(String userName=" + userName + ", int userAge=" + userAge + ")--");
        return newInstance(args);
    }

    public static DetailFragment newInstance(Bundle args) {
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        Log.d(TAG, "-->newInstance(Bundle args=" + args + ")--");
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            userName = args.getString(ExtraKeyConstans.NAME);
            userAge = args.getInt(ExtraKeyConstans.AGE);
        }
        Log.d(TAG, "-->onCreate()--userName=" + userName + ",userAge=" + userAge);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailTv = (TextView) view.findViewById(R.id.detail_tv);
        fillUserInfo(detailTv, userName, userAge);
    }

    private void fillUserInfo(TextView textView, String userName, int userAge) {
        if (textView == null) {
            return;
        }

        textView.setText("User Name:" + userName + "\nUser Age:" + userAge);
    }
}
