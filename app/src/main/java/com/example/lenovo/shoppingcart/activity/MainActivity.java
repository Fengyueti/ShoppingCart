package com.example.lenovo.shoppingcart.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.lenovo.shoppingcart.R;
import com.example.lenovo.shoppingcart.adapter.CartAdapter;
import com.example.lenovo.shoppingcart.api.Api;
import com.example.lenovo.shoppingcart.api.ProductApi;
import com.example.lenovo.shoppingcart.callback.CartUICallback;
import com.example.lenovo.shoppingcart.contract.CartContrat;
import com.example.lenovo.shoppingcart.entity.CartBean;
import com.example.lenovo.shoppingcart.presenter.CartPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CartContrat.ICartView,CartUICallback {

    private XRecyclerView rv;
    private CheckBox checkbox;
    private CartPresenter presenter;
    private CartAdapter adapter;
    private List<CartBean.Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv =findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        checkbox =findViewById(R.id.checkbox);
        presenter = new CartPresenter(this);
        HashMap<String,String> params=new HashMap<>();
        params.put("uid","71");
        presenter.getCarts(ProductApi.CART_URL,params);
        adapter = new CartAdapter(this);
        rv.setAdapter(adapter);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for (CartBean.Data cart : data) {
                        cart.ischecked=true;
                        for (CartBean.Data.Product product : cart.list) {
                            product.isproductChecked=true;
                        }
                    }
                }else{
                    for (CartBean.Data cart : data) {
                        cart.ischecked=false;
                        for (CartBean.Data.Product product : cart.list) {
                            product.isproductChecked=false;
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                getTotalPrice();
            }
        });
    }

    @Override
    public void success(String result) {
        final CartBean cartBean = new Gson().fromJson(result, CartBean.class);
        data= cartBean.getData();
        adapter.setCartCallback(this);
        adapter.setCarts(data);
        if(data!=null){
            for (CartBean.Data datum : data) {
                for (CartBean.Data.Product product : datum.list) {
                    product.productNum=1;
                }
            }
        }
    }

    @Override
    public void failure(String msg) {

    }
    /*
    * 获取总价
    * */
    private void getTotalPrice(){
        double totalprice=0;
        for (CartBean.Data datum : data) {
            for (CartBean.Data.Product product : datum.list) {
                if(product.isproductChecked){
                    totalprice+=product.getPrice()*product.productNum;
                }
            }
        }
        checkbox.setText("￥："+totalprice);
    }

    @Override
    public void notifyCart() {
        getTotalPrice();
    }
}
