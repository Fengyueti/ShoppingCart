package com.example.addcarts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.addcarts.R;
import com.example.addcarts.entity.SerachBean;

import java.util.ArrayList;
import java.util.List;

public class SerachAdapter extends RecyclerView.Adapter<SerachAdapter.MY> {
    private Context context;
    private List<SerachBean.Data> list;

    public SerachAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<SerachBean.Data> list) {
        if(list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SerachAdapter.MY onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.addcart, viewGroup, false);
        return new MY(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SerachAdapter.MY my, int i) {
        my.name.setText(list.get(i).getTitle());
        my.price.setText(list.get(i).getPrice()+"");
        final String[] split = list.get(i).getImages().split("!");
        Glide.with(context).load(split[0]).into(my.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MY extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private TextView price;
        private Button btn_search;
        public MY(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.addimg);
            name=itemView.findViewById(R.id.addname);
            price=itemView.findViewById(R.id.addprice);
            btn_search=itemView.findViewById(R.id.btn_add);
        }
    }
}
