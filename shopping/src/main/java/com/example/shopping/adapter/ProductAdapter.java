package com.example.shopping.adapter;

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
import com.example.shopping.R;
import com.example.shopping.callback.CartCallback;
import com.example.shopping.callback.CartUICallback;
import com.example.shopping.entity.CartBean;
import com.example.shopping.width.AddMinusView;
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
        viewHolder.name.setText(list.get(i).getTitle());
        viewHolder.price.setText("￥："+list.get(i).getPrice());
        viewHolder.checkBox.setChecked(list.get(i).isproductChecked);
        final String[] split = list.get(i).getImages().split("!");
        if(split!=null&&split.length>0){
            Glide.with(context).load(split[0]).into(viewHolder.img);
        }
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).isproductChecked=viewHolder.checkBox.isChecked();
                for (CartBean.Data.Product product : list) {
                    if(!product.isproductChecked){
                        cartCallback.notifyItem(false,product.pos);
                        return;
                    }
                    cartCallback.notifyItem(true,product.pos);
                }
            }
        });
        viewHolder.addMinusView.setAddminus(new AddMinusView.Addminus() {
            @Override
            public void addmin(int num) {
                list.get(i).productNum=num;
                cartCallback.nitifyNum();
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
        private TextView name;
        private TextView price;
        private AddMinusView addMinusView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
            addMinusView=itemView.findViewById(R.id.addminusView);
        }
    }
}
