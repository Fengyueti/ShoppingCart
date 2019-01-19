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
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ItemthreeAdapter extends XRecyclerView.Adapter<ItemthreeAdapter.Vh> {
    private Context context;
    private List<FirstpageBean.DataBean.MiaoshaBean.ListBean> list;

    public ItemthreeAdapter(Context context, List<FirstpageBean.DataBean.MiaoshaBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemthreeAdapter.Vh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.threeitem_one, viewGroup, false);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemthreeAdapter.Vh vh, int i) {
        vh.price.setText(list.get(i).getPrice());
        final String[] split = list.get(i).getImages().split("!");
        Glide.with(context).load(split[0]).into(vh.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView price;
        public Vh(@NonNull View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.price);
            img=itemView.findViewById(R.id.img1);
        }
    }
}
