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
import com.example.addcarts.entity.FirstpageBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private FirstpageBean.DataBean product;
    private final int TYPE_ONE = 0;
    private final int TYPE_TWO = 1;
    private final int TYPE_THREE = 2;
    private final int TYPE_FOUR = 3;
    public ShowAdapter(Context context) {
        this.context = context;
        this.product=new FirstpageBean.DataBean();
    }

    public void setProduct(FirstpageBean.DataBean product) {
        this.product = product;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return TYPE_ONE;
        }else if(position==1){
            return TYPE_TWO;
        }else if(position==2){
            return TYPE_THREE;
        }else {
            return TYPE_FOUR;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        if(getItemViewType(position)==TYPE_ONE){
            final View view = LayoutInflater.from(context).inflate(R.layout.oneitem, viewGroup, false);
            return new OneVh(view);
        }else if(getItemViewType(position)==TYPE_TWO){
            final View view = LayoutInflater.from(context).inflate(R.layout.twoitem, viewGroup, false);
            return new TwoVh(view);
        }else if(getItemViewType(position)==TYPE_THREE){
            final View view = LayoutInflater.from(context).inflate(R.layout.threeitem, viewGroup, false);
            return new ThreeVh(view);
        }else {
            final View view = LayoutInflater.from(context).inflate(R.layout.fouritem, viewGroup, false);
            return new FourVh(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(product.getBanner()!=null&&product.getFenlei()!=null&&product.getMiaosha()!=null&&product.getTuijian()!=null){
            if(viewHolder instanceof OneVh){
                List<String> list=new ArrayList<>();
                for (int i=0;i<product.getBanner().size();i++){
                    list.add(product.getBanner().get(i).getIcon());
                }
                ((OneVh) viewHolder).flyBanner.setImagesUrl(list);
            }else if(viewHolder instanceof TwoVh){
                ((TwoVh) viewHolder).rvv.setLayoutManager(new GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false));
                ((TwoVh) viewHolder).rvv.setAdapter(new ItemtwoAdapter(context,product.getFenlei()));
            }else if(viewHolder instanceof  ThreeVh){
                ((ThreeVh) viewHolder).name.setText(product.getMiaosha().getName());
                ((ThreeVh) viewHolder).threerv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                ((ThreeVh) viewHolder).threerv.setAdapter(new ItemthreeAdapter(context,product.getMiaosha().getList()));
            }else {
                ((FourVh) viewHolder).fourrv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                ((FourVh) viewHolder).fourrv.setAdapter(new ItemfourAdapter(context,product.getTuijian().getList()));
            }
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
   public class OneVh extends RecyclerView.ViewHolder{
        private FlyBanner flyBanner;
        public OneVh(@NonNull View itemView) {
            super(itemView);
            flyBanner=itemView.findViewById(R.id.fly);
        }
    }
    public class TwoVh extends RecyclerView.ViewHolder{
        private RecyclerView rvv;
        public TwoVh(@NonNull View itemView) {
            super(itemView);
            rvv=itemView.findViewById(R.id.tworv);
        }
    }
    public class ThreeVh extends RecyclerView.ViewHolder{
        private TextView name;
        private RecyclerView threerv;
        public ThreeVh(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            threerv=itemView.findViewById(R.id.threerv);
        }
    }
    public class FourVh extends RecyclerView.ViewHolder{
        private RecyclerView fourrv;
        public FourVh(@NonNull View itemView) {
            super(itemView);
            fourrv=itemView.findViewById(R.id.fourrv);
        }
    }
}
