package com.example.addcarts.contract;

import com.example.addcarts.net.RequestCallback;

import java.util.HashMap;

public interface AContract {
    public abstract class APresenter{
        public abstract void addshow(String api, HashMap<String,String> params);
        public abstract void addcartss(String api, HashMap<String,String> params);
        //public abstract void cartsshow(String api, HashMap<String,String> params);
    }
    interface AModel{
        void addshow(String api, HashMap<String,String> params, RequestCallback requestCallback);
        void addcartss(String api, HashMap<String,String> params, RequestCallback requestCallback);
        //void cartsshow(String api, HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface AView{
        void onsuccess(String result);
        void onfailure(String msg);
        void onSuccessCarts(String result);
    }
}
