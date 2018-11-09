package com.example.cxc.fullscreendemo.decoration.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cxc.fullscreendemo.R;
import com.example.cxc.fullscreendemo.decoration.po.ItemPO;

import java.util.List;

public class RecyclerViewTestAdapter extends RecyclerView.Adapter<TestBaseViewHolder> {
    private static final int TYPE_1 = 1;//横向排列名字和年龄
    private static final int TYPE_2 = 2;//竖向排列名字和年龄

    private List<ItemPO> items;

    @Override
    public int getItemViewType(int position) {
        ItemPO item = getItem(position);
        //age是奇数时使用TYPE_1,偶数时使用TYPE_2;
        return item != null && item.getAge() % 2 == 0 ? TYPE_2 : TYPE_1;
    }

    @Override
    public TestBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TestBaseViewHolder holder = null;
        switch (viewType) {
            case TYPE_1:
                View itemView1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_1_layout, parent, false);
                holder = new TestViewHolder(itemView1);
                break;
            case TYPE_2:
                View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_2_layout, parent, false);
                holder = new TestViewHolder(itemView2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(TestBaseViewHolder holder, int position) {
        ItemPO item = getItem(position);
        holder.fillData(item);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public ItemPO getItem(int position) {
        return position >= 0 && position < getItemCount() ? items.get(position) : null;
    }

    public void notifyDataSetChanged(List<ItemPO> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
