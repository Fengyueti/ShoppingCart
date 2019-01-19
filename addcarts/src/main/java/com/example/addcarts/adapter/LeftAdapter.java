package com.example.addcarts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.addcarts.R;
import com.example.addcarts.entity.LeftfenBean;

import java.util.ArrayList;
import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.MH> {
    private Context context;
    private List<LeftfenBean.Data> list;

    public LeftAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<LeftfenBean.Data> list) {
        if(list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LeftAdapter.MH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.leftitem, viewGroup, false);
        return new MH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeftAdapter.MH mh, final int i) {
         mh.name.setText(list.get(i).getName());
         mh.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 itemclick.click(list.get(i).getCid());
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MH extends RecyclerView.ViewHolder {
        private TextView name;
        public MH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.left_name);
        }
    }
    private Itemclick itemclick;

    public void setItemclick(Itemclick itemclick) {
        this.itemclick = itemclick;
    }

    public interface Itemclick{
        void click(int cid);
    }
}
