package com.example.lenovo.shoppingcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.shoppingcart.R;
import com.example.lenovo.shoppingcart.callback.CartCallback;
import com.example.lenovo.shoppingcart.callback.CartUICallback;
import com.example.lenovo.shoppingcart.entity.CartBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.MyVh> implements CartCallback {
    private Context context;
    private List<CartBean.Data> carts;
    private CartUICallback cartCallback;

    public void setCartCallback(CartUICallback cartCallback) {
        this.cartCallback = cartCallback;
    }

    public CartAdapter(Context context) {
        this.context = context;
        this.carts=new ArrayList<>();
    }

    public void setCarts(List<CartBean.Data> carts) {
        this.carts = carts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.MyVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.itemone, viewGroup, false);
        return new MyVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MyVh myVh, final int i) {

        for (CartBean.Data cart : carts) {
            for (CartBean.Data.Product product : cart.list) {
                product.pos = i;
            }
        }

        myVh.name.setText(carts.get(i).getSellerName());
        myVh.checkBox.setChecked(carts.get(i).ischecked);

        myVh.xRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        final ProductAdapter productAdapter = new ProductAdapter(context, carts.get(i).getList());
        productAdapter.setCartCallback(this);
        myVh.xRecyclerView.setAdapter(productAdapter);
        myVh.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //商家选中
                carts.get(i).ischecked=myVh.checkBox.isChecked();
                //carts.get(i).list，获得每个商家下的商品集合
                for (CartBean.Data.Product product : carts.get(i).list) {
                    product.isproductChecked=carts.get(i).ischecked;//商家选中，商家下的商品集合都选中
                }
                notifyDataSetChanged();
                if(cartCallback!=null){
                    cartCallback.notifyCart();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carts==null? 0:carts.size();
    }

    @Override
    public void notifyCartItem(boolean isChecked, int position) {
        carts.get(position).ischecked=isChecked;
        notifyDataSetChanged();
        if(cartCallback!=null){
            cartCallback.notifyCart();
        }
    }

    @Override
    public void notifyNum() {
        if(cartCallback!=null){
            cartCallback.notifyCart();
        }
    }

    public class MyVh extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView name;
        private XRecyclerView xRecyclerView;
        public MyVh(@NonNull View itemView) {
            super(itemView);
            xRecyclerView=itemView.findViewById(R.id.one_rv);
            checkBox=itemView.findViewById(R.id.one_checkbox);
            name=itemView.findViewById(R.id.name);
        }
    }
}
