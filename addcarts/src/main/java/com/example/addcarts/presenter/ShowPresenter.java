package com.example.addcarts.presenter;

import com.example.addcarts.contract.SContract;
import com.example.addcarts.model.ShowModel;
import com.example.addcarts.net.RequestCallback;

import java.util.HashMap;

public class ShowPresenter extends SContract.CPresenter {
    private ShowModel showModel;
    private SContract.SView sView;

    public ShowPresenter(SContract.SView sView) {
        this.showModel = new ShowModel();
        this.sView = sView;
    }

    @Override
    public void show(String api, HashMap<String, String> params) {
        if(showModel!=null){
            showModel.show(api, params, new RequestCallback() {
                @Override
                public void success(String result) {
                    if(sView!=null){
                        sView.success(result);
                    }
                }

                @Override
                public void failure(String msg) {
                    if(sView!=null){
                        sView.failure(msg);
                    }
                }
            });
        }
    }

    @Override
    public void show1(String api, HashMap<String, String> params) {
        if(showModel!=null){
            showModel.show1(api, params, new RequestCallback() {
                @Override
                public void success(String result) {
                    if(sView!=null){
                        sView.success1(result);
                    }
                }

                @Override
                public void failure(String msg) {
                    if(sView!=null){
                        sView.failure(msg);
                    }
                }
            });
        }
    }

    public void destroy(){
        if(sView!=null){
            sView=null;
        }
    }
}
