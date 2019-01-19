package com.example.shopping.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.shopping.R;
import com.example.shopping.adapter.CartAdapter;
import com.example.shopping.api.ProductApi;
import com.example.shopping.callback.CartUICallback;
import com.example.shopping.contract.CartContrat;
import com.example.shopping.entity.CartBean;
import com.example.shopping.presenter.CPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CartContrat.ICartView,CartUICallback {

    private XRecyclerView rv;
    private CheckBox checkbox;
    private CPresenter presenter;
    private CartAdapter cartAdapter;
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
        cartAdapter = new CartAdapter(this,this);
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
                cartAdapter.notifyDataSetChanged();
                getTotalPrice();
            }
        });
        rv.setAdapter(cartAdapter);
    }

    @Override
    public void success(String result) {

        final CartBean cartBean = new Gson().fromJson(result, CartBean.class);
        data = cartBean.getData();
        for (CartBean.Data datum : data) {
            for (CartBean.Data.Product product : datum.list) {
                product.productNum = 1;
            }
        }
        cartAdapter.setList(data);
    }

    @Override
    public void failure(String msg) {

    }
    /*
    * 获取总价
    * */
    public void getTotalPrice(){
        double totalprice=0;
        for (CartBean.Data datum : data) {
            for (CartBean.Data.Product product : datum.list) {
                if(product.isproductChecked) {
                    totalprice += product.price*product.productNum;
                }
            }
        }
        checkbox.setText("￥："+totalprice);
    }

    @Override
    public void notifyCard() {
        getTotalPrice();
    }
}
