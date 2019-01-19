package com.example.addcarts.contract;

import com.example.addcarts.net.RequestCallback;

import java.util.HashMap;

public interface SContract {
    public abstract class CPresenter{
        public abstract void show(String api, HashMap<String,String> params);
        public abstract void show1(String api, HashMap<String,String> params);
    }
    interface SModel{
        void show(String api, HashMap<String,String> params, RequestCallback requestCallback);
        void show1(String api, HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface SView{
        void success(String result);
        void success1(String result);
        void failure(String msg);
    }
}
