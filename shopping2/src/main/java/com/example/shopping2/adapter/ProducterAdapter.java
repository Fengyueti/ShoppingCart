package com.example.shopping2.adapter;

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
import com.example.shopping2.R;
import com.example.shopping2.callback.CartCallback;
import com.example.shopping2.entity.CartBean;
import com.example.shopping2.width.AddminsView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class ProducterAdapter extends XRecyclerView.Adapter<ProducterAdapter.VH> {
    private Context context;
    private List<CartBean.Data.Product> list;
    private CartCallback cartCallback;

    public ProducterAdapter(Context context, List<CartBean.Data.Product> list, CartCallback cartCallback) {
        this.context = context;
        this.list = list;
        this.cartCallback = cartCallback;
    }

    @NonNull
    @Override
    public ProducterAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(context).inflate(R.layout.productitem, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProducterAdapter.VH vh, final int i) {
        vh.checkBox.setChecked(list.get(i).isproductChecked);
        vh.title.setText(list.get(i).getTitle());
        vh.price.setText("￥："+list.get(i).getPrice());
        final String[] split = list.get(i).getImages().split("!");
        Glide.with(context).load(split[0]).into(vh.img);
        vh.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(i).isproductChecked=vh.checkBox.isChecked();
                for (CartBean.Data.Product product : list) {
                    if(!product.isproductChecked){
                        cartCallback.notifyItem(false,product.pos);
                        return;
                    }
                    cartCallback.notifyItem(true,product.pos);
                }
            }
        });
        vh.addminsView.setAdadmin(new AddminsView.Adadmin() {
            @Override
            public void addmins(int num) {
                list.get(i).productNum=num;
                cartCallback.nitifyNum();
            }
        });

        vh.addminsView.setNum(list.get(i).productNum);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private ImageView img;
        private TextView title;
        private TextView price;
        private AddminsView addminsView;
        public VH(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.pro_check);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
            addminsView=itemView.findViewById(R.id.addminview);
        }
    }
}
