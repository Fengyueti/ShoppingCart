package com.example.shopping2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shopping2.R;
import com.example.shopping2.callback.CartCallback;
import com.example.shopping2.callback.CartUICallback;
import com.example.shopping2.entity.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.MYSH> implements CartCallback {
    private Context context;
    private List<CartBean.Data> carts;
    private CartUICallback cartUICallback;
    public CartAdapter(Context context,CartUICallback cartUICallback) {
        this.context = context;
        carts=new ArrayList<>();
        this.cartUICallback=cartUICallback;
    }

    public void setCarts(List<CartBean.Data> carts) {
        if(carts!=null) {
            this.carts = carts;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.MYSH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.itemone, viewGroup, false);
        return new MYSH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MYSH mysh, final int i) {
        for (CartBean.Data.Product product : carts.get(i).list) {
           product.setPos(i);
        }
        mysh.name.setText(carts.get(i).getSellerName());
        mysh.onerv.setLayoutManager(new LinearLayoutManager(context));
        final ProducterAdapter producterAdapter = new ProducterAdapter(context, carts.get(i).list,this);
        mysh.onerv.setAdapter(producterAdapter);
        mysh.checkBox.setChecked(carts.get(i).ischecked);
        mysh.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carts.get(i).ischecked=mysh.checkBox.isChecked();
                for (CartBean.Data.Product product : carts.get(i).list) {
                    product.isproductChecked=carts.get(i).ischecked;
                }
                notifyDataSetChanged();
                if(cartUICallback!=null){
                    cartUICallback.notifyCard();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    @Override
    public void nitifyNum() {
        if(cartUICallback!=null){
            cartUICallback.notifyCard();
        }
    }

    @Override
    public void notifyItem(boolean isChecked, int position) {
        carts.get(position).ischecked=isChecked;

        if(cartUICallback!=null){
            cartUICallback.notifyCard();
        }
        notifyDataSetChanged();
    }

    public class MYSH extends RecyclerView.ViewHolder {
        private TextView name;
        private XRecyclerView onerv;
        private CheckBox checkBox;
        public MYSH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            checkBox=itemView.findViewById(R.id.one_checkbox);
            onerv=itemView.findViewById(R.id.one_rv);
        }
    }
}
