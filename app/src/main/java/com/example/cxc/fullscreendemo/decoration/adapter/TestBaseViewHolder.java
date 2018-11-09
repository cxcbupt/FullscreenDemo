package com.example.cxc.fullscreendemo.decoration.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.cxc.fullscreendemo.R;
import com.example.cxc.fullscreendemo.decoration.po.ItemPO;

public abstract class TestBaseViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTv;
    private TextView ageTv;

    public TestBaseViewHolder(View itemView) {
        super(itemView);
        initView(itemView);
    }

    protected void initView(View itemView) {
        nameTv = itemView.findViewById(R.id.name_tv);
        ageTv = itemView.findViewById(R.id.age_tv);
    }

    public void fillData(ItemPO item) {
        if (item == null) {
            return;
        }

        if (nameTv != null) {
            nameTv.setText(item.getName());
        }

        if (ageTv != null) {
            ageTv.setText(String.valueOf(item.getAge()));
        }
    }
}
