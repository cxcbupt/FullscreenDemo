package com.example.cxc.fullscreendemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View detailBtn = findViewById(R.id.detail_btn);
        detailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDetailBtnClick();
            }
        });
    }

    private void onDetailBtnClick() {
        Intent intent = new Intent(this, DetailActivity.class);
        //传参
        intent.putExtra(ExtraKeyConstans.NAME, "CXC");
        intent.putExtra(ExtraKeyConstans.AGE, 22);

        /*//传参
        Bundle extras = new Bundle();
        extras.putString(ExtraKeyConstans.NAME, "CXC");
        extras.putInt(ExtraKeyConstans.AGE, 22);
        intent.putExtras(extras);*/
        startActivity(intent);
    }
}
