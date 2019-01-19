package com.example.lenovo.shoppingcart.contract;

import com.example.lenovo.shoppingcart.net.ICartmodelCallback;

import java.util.HashMap;

public interface CartContrat {
    public abstract class CartPresenter{
        public abstract void getCarts(String api, HashMap<String,String> params);
    }
    interface ICartModel{
        void getCarts(String api, HashMap<String,String> params, ICartmodelCallback iCartmodelCallback);
    }
    interface ICartView{
        void success(String result);
        void failure(String msg);
    }
}
