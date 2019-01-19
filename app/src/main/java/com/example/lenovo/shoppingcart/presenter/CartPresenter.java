package com.example.lenovo.shoppingcart.presenter;

import com.example.lenovo.shoppingcart.contract.CartContrat;
import com.example.lenovo.shoppingcart.model.CartModel;
import com.example.lenovo.shoppingcart.net.ICartmodelCallback;

import java.util.HashMap;

public class CartPresenter extends CartContrat.CartPresenter {
    private CartModel cartModel;
    private CartContrat.ICartView iCartView;

    public CartPresenter(CartContrat.ICartView iCartView) {
        this.cartModel = new CartModel();
        this.iCartView = iCartView;
    }

    @Override
    public void getCarts(String api, HashMap<String, String> params) {
        if(cartModel!=null){
            cartModel.getCarts(api, params, new ICartmodelCallback() {
                @Override
                public void success(String result) {
                    if(iCartView!=null){
                        iCartView.success(result);
                    }
                }

                @Override
                public void failure(String msg) {
                    if(iCartView!=null){
                        iCartView.failure(msg);
                    }
                }
            });
        }
    }
}
