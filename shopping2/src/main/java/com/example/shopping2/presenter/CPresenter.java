package com.example.shopping2.presenter;

import com.example.shopping2.contract.CartContrat;
import com.example.shopping2.model.CModel;
import com.example.shopping2.net.RequestCallback;

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
