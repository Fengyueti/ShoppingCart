package com.example.lenovo.shoppingcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.shoppingcart.R;
import com.example.lenovo.shoppingcart.callback.CartCallback;
import com.example.lenovo.shoppingcart.entity.CartBean;
import com.example.lenovo.shoppingcart.width.AddMinusView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ProductAdapter extends XRecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<CartBean.Data.Product> list;

    private CartCallback cartCallback;

    public void setCartCallback(CartCallback cartCallback) {
        this.cartCallback = cartCallback;
    }

    public ProductAdapter(Context context, List<CartBean.Data.Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.product_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ViewHolder viewHolder, final int i) {
        final CartBean.Data.Product product = list.get(i);
        viewHolder.title.setText(product.getTitle());
        viewHolder.price.setText("￥："+product.getPrice());
        viewHolder.checkBox.setChecked(product.isproductChecked);
        final String[] imgs = product.images.split("!");
        if(imgs!=null&&imgs.length>0){
            Glide.with(context).load(imgs[0]).into(viewHolder.img);
        }
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).isproductChecked=viewHolder.checkBox.isChecked();
                for (CartBean.Data.Product product1 : list) {
                    if (!product1.isproductChecked){
                        cartCallback.notifyCartItem(false,product1.getPos());
                        return;
                    }
                    cartCallback.notifyCartItem(true,product1.getPos());
                }
            }
        });
        viewHolder.addMinusView.setAddMinsCallback(new AddMinusView.AddMinsCallback() {
            @Override
            public void numCallback(int num) {
                list.get(i).productNum=num;
                if (cartCallback!=null){
                    cartCallback.notifyNum();
                }
            }
        });
        viewHolder.addMinusView.setNum(list.get(i).productNum);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private ImageView img;
        private TextView title;
        private TextView price;
        private AddMinusView addMinusView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
            addMinusView=itemView.findViewById(R.id.addminusView);
        }
    }
}
