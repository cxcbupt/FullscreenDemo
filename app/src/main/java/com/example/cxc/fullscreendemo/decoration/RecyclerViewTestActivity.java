package com.example.cxc.fullscreendemo.decoration;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cxc.fullscreendemo.R;
import com.example.cxc.fullscreendemo.decoration.adapter.RecyclerViewTestAdapter;
import com.example.cxc.fullscreendemo.decoration.po.ItemPO;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTestActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewTestActivity";

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(R.layout.activity_item_decoration_test);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //设置LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        //设置Adapter
        RecyclerViewTestAdapter adapter = new RecyclerViewTestAdapter();
        mRecyclerView.setAdapter(adapter);

        //设置数据
        List<ItemPO> items = fakeItems();
        adapter.notifyDataSetChanged(items);
    }


    private static final int ITEM_COUNT = 100;

    private static List<ItemPO> fakeItems() {
        List<ItemPO> items = new ArrayList<>(ITEM_COUNT);

        for (int i = 0; i < ITEM_COUNT; i++) {
            ItemPO tItem = new ItemPO("Name_" + i, i);
            items.add(tItem);
        }

        return items;
    }
}
