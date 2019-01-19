package com.example.addcarts.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.addcarts.R;
import com.example.addcarts.adapter.LeftAdapter;
import com.example.addcarts.adapter.RightAdapter;
import com.example.addcarts.api.UserApi;
import com.example.addcarts.contract.SContract;
import com.example.addcarts.entity.LeftfenBean;
import com.example.addcarts.entity.RightBean;
import com.example.addcarts.presenter.ShowPresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class FragmentTwo extends Fragment implements SContract.SView {

    private RecyclerView leftrv;
    private RecyclerView rightrv;
    private LeftAdapter adapter;
    private ShowPresenter presenter;
    private RightAdapter adapter1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmenttwo, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        leftrv =view.findViewById(R.id.leftrv);
        rightrv =view.findViewById(R.id.rightrv);
        leftrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rightrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LeftAdapter(getActivity());
        adapter1 = new RightAdapter(getActivity());
        presenter = new ShowPresenter(this);
        presenter.show1(UserApi.RIGHT_API,null);
        presenter.show(UserApi.LEFTFEN_API,null);
        leftrv.setAdapter(adapter);
        rightrv.setAdapter(adapter1);
        adapter.setItemclick(new LeftAdapter.Itemclick() {
            @Override
            public void click(int cid) {
                HashMap<String,String> params=new HashMap<>();
                params.put("cid",cid+"");
                presenter.show1(UserApi.RIGHT_API,params);
            }
        });
    }

    @Override
    public void success(String result) {
        final LeftfenBean leftfenBean = new Gson().fromJson(result, LeftfenBean.class);

        final List<LeftfenBean.Data> data = leftfenBean.getData();
        adapter.setList(data);

    }

    @Override
    public void success1(String result) {
        final RightBean rightBean = new Gson().fromJson(result, RightBean.class);
        final List<RightBean.Data> data1 = rightBean.getData();
        adapter1.setList(data1);
    }

    @Override
    public void failure(String msg) {

    }
}
