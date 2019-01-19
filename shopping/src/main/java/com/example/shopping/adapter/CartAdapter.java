package com.example.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.shopping.R;
import com.example.shopping.callback.CartCallback;
import com.example.shopping.callback.CartUICallback;
import com.example.shopping.entity.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.MYVH> implements CartCallback {
    private Context context;
    private List<CartBean.Data> carts;
    private CartUICallback cartUICallback;

    public void setCartUICallback(CartUICallback cartUICallback) {
        this.cartUICallback = cartUICallback;
    }

    public CartAdapter(Context context,CartUICallback cartUICallback) {
        this.context = context;
        carts=new ArrayList<>();
        this.cartUICallback=cartUICallback;
    }

    public void setList(List<CartBean.Data> list) {
        if(list!=null) {
            this.carts = list;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.MYVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.itemone, viewGroup, false);
        return new MYVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MYVH myvh, final int i) {

        for (CartBean.Data cart : carts) {
            for (CartBean.Data.Product product : cart.list) {
                product.setPos(i);
            }
        }
        myvh.one_check.setChecked(carts.get(i).ischecked);
        myvh.name.setText(carts.get(i).getSellerName());
        myvh.one_rv.setLayoutManager(new LinearLayoutManager(context));
        final ProductAdapter productAdapter = new ProductAdapter(context, carts.get(i).getList());
        productAdapter.setCartCallback(this);
        myvh.one_rv.setAdapter(productAdapter);
        //点击商家，商家商品全部选中
        myvh.one_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                carts.get(i).ischecked=myvh.one_check.isChecked();
                for (CartBean.Data.Product product : carts.get(i).list) {
                    product.isproductChecked=carts.get(i).ischecked;//商家选中，商家下的商品集合都选中
                }
                notifyDataSetChanged();
                if (cartUICallback!=null){
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
        if (cartUICallback!=null){
            cartUICallback.notifyCard();
        }
    }

    @Override
    public void notifyItem(boolean isChecked, int position) {
        carts.get(position).ischecked=isChecked;
        notifyDataSetChanged();
        if (cartUICallback!=null){
            cartUICallback.notifyCard();
        }
        notifyDataSetChanged();
    }

    public class MYVH extends RecyclerView.ViewHolder {
        private TextView name;
        private XRecyclerView one_rv;
        private CheckBox one_check;

        public MYVH(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            one_rv=itemView.findViewById(R.id.one_rv);
            one_check = itemView.findViewById(R.id.one_check);
        }
    }
}
