package com.example.addcarts.presenter;

import com.example.addcarts.contract.AContract;
import com.example.addcarts.model.SerachModel;
import com.example.addcarts.net.RequestCallback;

import java.util.HashMap;

public class SerachPresenter extends AContract.APresenter {
    private SerachModel serachModel;
    private AContract.AView aView;

    public SerachPresenter(AContract.AView aView) {
        this.serachModel = new SerachModel();
        this.aView = aView;
    }

    @Override
    public void addshow(String api, HashMap<String, String> params) {
        if(serachModel!=null){
            serachModel.addshow(api, params, new RequestCallback() {
                @Override
                public void success(String result) {
                    if(aView!=null){
                        aView.onsuccess(result);
                    }
                }

                @Override
                public void failure(String msg) {
                    if(aView!=null){
                        aView.onfailure(msg);
                    }
                }
            });
        }
    }
    public void destroy(){
        if(aView!=null){
            aView=null;
        }
    }
}
