package com.example.shopping.presenter;

import com.example.shopping.contract.CartContrat;
import com.example.shopping.model.CModel;
import com.example.shopping.net.RequestCallback;

import java.util.HashMap;

public class CPresenter extends CartContrat.CartPresenter {
    private CModel cModel;
    private CartContrat.ICartView iCartView;

    public CPresenter(CartContrat.ICartView iCartView) {
        this.cModel = new CModel();
        this.iCartView = iCartView;
    }

    @Override
    public void getCarts(String api, HashMap<String, String> params) {
        if(cModel!=null){
            cModel.getCarts(api, params, new RequestCallback() {
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
    public void destroy(){
        if(iCartView!=null){
            iCartView=null;
        }
    }
}
