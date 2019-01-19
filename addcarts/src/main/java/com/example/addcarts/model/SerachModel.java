package com.example.addcarts.model;

import android.os.Handler;

import com.example.addcarts.contract.AContract;
import com.example.addcarts.net.OkhttpCallback;
import com.example.addcarts.net.OkhttpUtils;
import com.example.addcarts.net.RequestCallback;

import java.util.HashMap;

public class SerachModel implements AContract.AModel {
    Handler handler=new Handler();
    @Override
    public void addshow(String api, HashMap<String, String> params, final RequestCallback requestCallback) {
        OkhttpUtils.getmInstance().doPost(api, params, new OkhttpCallback() {
            @Override
            public void success(final String result) {
                if(requestCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.success(result);
                        }
                    });
                }
            }

            @Override
            public void failure(String msg) {
                if(requestCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.failure("网络不稳定");
                        }
                    });
                }
            }
        });
    }
}
