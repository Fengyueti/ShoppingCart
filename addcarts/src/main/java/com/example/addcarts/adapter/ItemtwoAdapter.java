package com.example.addcarts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.addcarts.R;
import com.example.addcarts.entity.FirstpageBean;


import java.util.List;

public class ItemtwoAdapter extends RecyclerView.Adapter<ItemtwoAdapter.Vh> {
    private Context context;
    private List<FirstpageBean.DataBean.FenleiBean> list;

    public ItemtwoAdapter(Context context, List<FirstpageBean.DataBean.FenleiBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemtwoAdapter.Vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.twoitem_one, viewGroup, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemtwoAdapter.Vh vh, int i) {
        vh.name.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon()).into(vh.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        public Vh(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.twoitem_name);
            img=itemView.findViewById(R.id.img);
        }
    }
}
