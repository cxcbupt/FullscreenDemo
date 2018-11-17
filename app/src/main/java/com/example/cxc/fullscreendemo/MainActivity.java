package com.example.cxc.fullscreendemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.cxc.fullscreendemo.decoration.RecyclerViewTestActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View detailBtn = findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(v -> onDetailBtnClick());

        View recyclerBtn = findViewById(R.id.recycler_view_btn);
        recyclerBtn.setOnClickListener(v -> onTestRecyclerViewBtnClick());
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
}
