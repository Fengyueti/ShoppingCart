package com.example.shopping.contract;

import com.example.shopping.net.RequestCallback;

import java.util.HashMap;

public interface CartContrat {
    public abstract class CartPresenter{
        public abstract void getCarts(String api, HashMap<String,String> params);
    }
    interface ICartModel{
        void getCarts(String api, HashMap<String, String> params, RequestCallback requestCallback);
    }
    interface ICartView{
        void success(String result);
        void failure(String msg);
    }
}
