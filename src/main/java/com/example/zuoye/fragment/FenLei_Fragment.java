package com.example.moni.fragment;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moni.GoodsDescActivity;
import com.example.moni.R;
import com.example.moni.adapter.Rec_fenleiAdapter;
import com.example.moni.base.BaseFragment;
import com.example.moni.interfaces.IPersenter;
import com.example.moni.interfaces.fenleiitem.FenLeiItemContract;
import com.example.moni.model.bean.FenLei_TabBean;
import com.example.moni.percenter.FenLeiItemPersenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class FenLei_Fragment extends BaseFragment implements FenLeiItemContract.View {
    private List<FenLei_TabBean.DataBean.CategoryListBean> tablist;
    private RecyclerView rec;
    private Rec_fenleiAdapter adapter;
    private ImageView img_fenlei;
    private TextView tv_fenlei;
    private int posi = 0;

    @Override
    protected int getLayout() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        return R.layout.fragment_fenlei_;
    }

    @Override
    protected void initView(View view) {
        rec = view.findViewById(R.id.rec_fenlei);
        img_fenlei = view.findViewById(R.id.iv_fenlei);
        tv_fenlei = view.findViewById(R.id.tv_fenlei_lei);
        rec.setLayoutManager(new GridLayoutManager(getActivity(),3));

        tablist = new ArrayList<>();
        adapter = new Rec_fenleiAdapter(tablist, getActivity());
        rec.setAdapter(adapter);

        adapter.setOnClick(new Rec_fenleiAdapter.OnClick() {
            @Override
            public void OnItemClick(int position) {
                ArrayList<FenLei_TabBean.DataBean.CategoryListBean> list = new ArrayList<>();
                list.addAll(tablist);
                Intent intent = new Intent(context, GoodsDescActivity.class);
                intent.putExtra("data",list);
                intent.putExtra("posi",position);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        ((FenLeiItemPersenter)persenter).getFenLeiItemData(posi);
    }

    @Subscribe
    public void getPosi(String position){
        posi = Integer.parseInt(position);
        ((FenLeiItemPersenter)persenter).getFenLeiItemData(posi);
    }

    @Override
    protected IPersenter createPersenter() {
        return new FenLeiItemPersenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void FenLeiItemDataReturn(FenLei_TabBean fenLei_tabBean) {
        List<FenLei_TabBean.DataBean.CategoryListBean> categoryList = fenLei_tabBean.getData().getCategoryList();
        tv_fenlei.setText(categoryList.get(posi).getFront_desc());
        Glide.with(context).load(categoryList.get(posi).getWap_banner_url()).into(img_fenlei);
        tablist.clear();
        tablist.addAll(categoryList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
