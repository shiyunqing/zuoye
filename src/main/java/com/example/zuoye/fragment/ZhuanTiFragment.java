package com.example.moni.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moni.R;
import com.example.moni.adapter.Rec_zhuantiAdapter;
import com.example.moni.base.BaseFragment;
import com.example.moni.interfaces.IBaseView;
import com.example.moni.interfaces.IPersenter;
import com.example.moni.interfaces.home.HomeContract;
import com.example.moni.model.bean.ShouYeBean;
import com.example.moni.percenter.HomePersenter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZhuanTiFragment extends BaseFragment implements HomeContract.View {


    private RecyclerView rec;
    private List<ShouYeBean.DataBean.TopicListBean> list;
    private Rec_zhuantiAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_zhuanti;
    }

    @Override
    protected void initView(View view) {
        rec = view.findViewById(R.id.rec_zhuanti);
        rec.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        adapter = new Rec_zhuantiAdapter(list, context);
        rec.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        ((HomePersenter)persenter).getHomeData();
    }

    @Override
    protected IPersenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void HomeDataReturn(ShouYeBean shouYeBean) {
        List<ShouYeBean.DataBean.TopicListBean> topicList = shouYeBean.getData().getTopicList();
        list.addAll(topicList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
