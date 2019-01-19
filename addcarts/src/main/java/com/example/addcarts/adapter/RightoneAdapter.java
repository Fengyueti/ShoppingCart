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
import com.example.addcarts.entity.RightBean;

import java.util.List;

public class RightoneAdapter extends RecyclerView.Adapter<RightoneAdapter.MS> {
    private Context context;
    private List<RightBean.Data.LL> list;

    public RightoneAdapter(Context context, List<RightBean.Data.LL> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RightoneAdapter.MS onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.rightitem_one, viewGroup, false);
        return new MS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightoneAdapter.MS ms, int i) {
        ms.name.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon()).into(ms.img);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MS extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        public MS(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.right_img);
            name=itemView.findViewById(R.id.rg_name);
        }
    }
}
