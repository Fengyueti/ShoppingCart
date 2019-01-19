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
import android.widget.Button;
import android.widget.EditText;

import com.example.addcarts.R;
import com.example.addcarts.adapter.SerachAdapter;
import com.example.addcarts.api.UserApi;
import com.example.addcarts.contract.AContract;
import com.example.addcarts.entity.SerachBean;
import com.example.addcarts.presenter.SerachPresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

public class FragmentThree extends Fragment implements AContract.AView {
    private EditText sou;
    private RecyclerView rv;
    private SerachAdapter adapter;
    private SerachPresenter presenter;
    private int page=1;
    private Button search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragmentthree, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sou=view.findViewById(R.id.ed_sou);
        search =view.findViewById(R.id.search);
        rv=view.findViewById(R.id.ft_rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SerachAdapter(getActivity());
        presenter =new SerachPresenter(this);
        rv.setAdapter(adapter);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ss = sou.getText().toString();
                HashMap<String,String> params=new HashMap<>();
                params.put("keywords",ss);
                params.put("page",page+"");
                presenter.addshow(UserApi.SOU_API,params);
            }
        });
    }

    @Override
    public void onsuccess(String result) {
        final SerachBean serachBean = new Gson().fromJson(result, SerachBean.class);
        final List<SerachBean.Data> data = serachBean.getData();
        adapter.setList(data);
    }

    @Override
    public void onfailure(String msg) {

    }
}
