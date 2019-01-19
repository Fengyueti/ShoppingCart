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
import com.example.addcarts.adapter.ShowAdapter;
import com.example.addcarts.api.UserApi;
import com.example.addcarts.contract.SContract;
import com.example.addcarts.entity.FirstpageBean;
import com.example.addcarts.presenter.ShowPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.List;

public class FragmentOne extends Fragment implements SContract.SView {

    private XRecyclerView rv;
    private ShowAdapter adapter;
    private ShowPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmentone, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ShowAdapter(getActivity());
        presenter = new ShowPresenter(this);
        presenter.show(UserApi.FIRST_PAGE,null);
        rv.setAdapter(adapter);
    }

    @Override
    public void success(String result) {
        final FirstpageBean firstpageBean = new Gson().fromJson(result, FirstpageBean.class);
        final FirstpageBean.DataBean data = firstpageBean.getData();
        adapter.setProduct(data);
    }

    @Override
    public void success1(String result) {

    }

    @Override
    public void failure(String msg) {

    }
}
