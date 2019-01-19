package com.example.lenovo.shoppingcart.model;

import android.os.Handler;

import com.example.lenovo.shoppingcart.contract.CartContrat;
import com.example.lenovo.shoppingcart.net.ICartmodelCallback;
import com.example.lenovo.shoppingcart.net.OKhttpUtils;
import com.example.lenovo.shoppingcart.net.OkhttpCallback;

import java.util.HashMap;


public class CartModel implements CartContrat.ICartModel {
    Handler handler=new Handler();
    @Override
    public void getCarts(String api, HashMap<String, String> params, final ICartmodelCallback iCartmodelCallback) {
      OKhttpUtils.getmInstance().doPost(api, params, new OkhttpCallback() {
          @Override
          public void success(final String result) {
              if(iCartmodelCallback!=null){
                  handler.post(new Runnable() {
                      @Override
                      public void run() {
                          iCartmodelCallback.success(result);
                      }
                  });
              }
          }

          @Override
          public void failure(String msg) {
            if(iCartmodelCallback!=null){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iCartmodelCallback.failure("网络不稳定");
                    }
                });
            }
          }
      });
    }
}
