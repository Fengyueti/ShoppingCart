package com.example.shopping2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.shopping2.R;
import com.example.shopping2.adapter.CartAdapter;
import com.example.shopping2.api.ProductApi;
import com.example.shopping2.callback.CartUICallback;
import com.example.shopping2.contract.CartContrat;
import com.example.shopping2.entity.CartBean;
import com.example.shopping2.presenter.CPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CartContrat.ICartView,CartUICallback {

    private XRecyclerView rv;
    private CheckBox checkbox;
    private CPresenter presenter;
    private CartAdapter adapter;
    private List<CartBean.Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv =findViewById(R.id.rv);
        checkbox =findViewById(R.id.checkbox);
        rv.setLayoutManager(new LinearLayoutManager(this));
        presenter = new CPresenter(this);
        HashMap<String,String> params=new HashMap<>();
        params.put("uid","71");
        presenter.getCarts(ProductApi.CART_URL,params);
        adapter = new CartAdapter(this,this);
        rv.setAdapter(adapter);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for (CartBean.Data datum : data) {
                        datum.ischecked=true;
                        for (CartBean.Data.Product product : datum.list) {
                            product.isproductChecked=true;
                        }
                    }
                }else{
                    for (CartBean.Data datum : data) {
                        datum.ischecked=false;
                        for (CartBean.Data.Product product : datum.list) {
                            product.isproductChecked=false;
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                getTotal();
            }
        });
    }

    @Override
    public void success(String result) {
        final CartBean cartBean = new Gson().fromJson(result, CartBean.class);
        data = cartBean.getData();
        for (CartBean.Data datum : data) {
            for (CartBean.Data.Product product : datum.list) {
                product.productNum=1;
            }
        }
        adapter.setCarts(data);
    }

    @Override
    public void failure(String msg) {

    }
    public void getTotal(){
        double totalprice=0;
        for (CartBean.Data datum : data) {
            for (CartBean.Data.Product product : datum.list) {
                if(product.isproductChecked) {
                    totalprice += product.price * product.productNum;
                }
            }
        }
        checkbox.setText("￥："+totalprice);
    }

    @Override
    public void notifyCard() {
        getTotal();
    }
}
