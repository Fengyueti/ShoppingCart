package com.example.addcarts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.addcarts.R;
import com.example.addcarts.entity.RightBean;

import java.util.ArrayList;
import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MS> {
    private Context context;
    private List<RightBean.Data> list;

    public RightAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<RightBean.Data> list) {
        if(list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightAdapter.MS onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.rightitem, viewGroup, false);
        return new MS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RightAdapter.MS ms, int i) {
        ms.name.setText(list.get(i).getName());
        ms.rv.setLayoutManager(new GridLayoutManager(context,3));
        ms.rv.setAdapter(new RightoneAdapter(context,list.get(i).getList()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MS extends RecyclerView.ViewHolder {
        private TextView name;
        private RecyclerView rv;
        public MS(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.right_name);
            rv=itemView.findViewById(R.id.right_rv);
        }
    }
}
