package com.example.shopping.model;

import android.os.Handler;

import com.example.shopping.contract.CartContrat;
import com.example.shopping.net.OkhttpCallback;
import com.example.shopping.net.OkhttpUtils;
import com.example.shopping.net.RequestCallback;

import java.util.HashMap;

public class CModel implements CartContrat.ICartModel {
    Handler handler=new Handler();
    @Override
    public void getCarts(String api, HashMap<String, String> params, final RequestCallback requestCallback) {
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
                if (requestCallback!=null){
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
