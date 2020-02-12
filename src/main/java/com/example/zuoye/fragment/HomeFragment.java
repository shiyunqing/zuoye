package com.example.moni.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.moni.R;
import com.example.moni.adapter.Rec_shouye_yisiAdapter;
import com.example.moni.adapter.Rec_shouye_zhigongAdapter;
import com.example.moni.base.BaseFragment;
import com.example.moni.interfaces.IBaseView;
import com.example.moni.interfaces.IPersenter;
import com.example.moni.interfaces.home.HomeContract;
import com.example.moni.model.bean.ShouYeBean;
import com.example.moni.model.apis.HomeApi;
import com.example.moni.percenter.HomePersenter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;



public class HomeFragment extends BaseFragment implements HomeContract.View {
    private Banner banner;
    private TabLayout tabShouye;
    private RecyclerView rec_shouye_zhigong;
    private Rec_shouye_zhigongAdapter adapter;
    private RecyclerView yisi;
    private Rec_shouye_yisiAdapter yisiAdapter;
    private TextView tvZhigong;
    private TextView tvYisi;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        tabShouye = (TabLayout) view.findViewById(R.id.tab_shouye);
        rec_shouye_zhigong = (RecyclerView) view.findViewById(R.id.rec_shouye_zhigong);
        yisi = (RecyclerView) view.findViewById(R.id.rec_shouye_yisi);
        tvZhigong = (TextView) view.findViewById(R.id.tv_zhigong);
        tvYisi = (TextView) view.findViewById(R.id.tv_yisi);
        rec_shouye_zhigong.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        yisi.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    /*private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(HomeApi.url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final ShouYeBean shouYeBean = new Gson().fromJson(json, ShouYeBean.class);
                List<ShouYeBean.DataBean.BannerBean> banners = shouYeBean.getData().getBanner();

                //banner图片资源的集合
                final ArrayList<String> imgs = new ArrayList<>();
                for (int i = 0; i < banners.size(); i++) {
                    String image_url = banners.get(i).getImage_url();
                    imgs.add(image_url);
                }

                //定义tab集合
                final ArrayList<String> tabs = new ArrayList<>();
                List<ShouYeBean.DataBean.CategoryListBean> categoryList = shouYeBean.getData().getCategoryList();
                for (int i = 0; i < categoryList.size(); i++) {
                    String name = categoryList.get(i).getName();
                    tabs.add(name);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        tvYisi.setText("周一周四·新品首发");
                        tvZhigong.setText("品牌制造商直供");

                        //banner轮播图的实现
                        banner.setImages(imgs).setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                Glide.with(context).load((String) path).into(imageView);
                            }
                        }).start();

                        //banner的点击监听
                        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                        //tab的显示
                        for (int i = 0; i < tabs.size(); i++) {
                            tabShouye.addTab(tabShouye.newTab().setText(tabs.get(i)));
                        }

                        //rec直供的适配器
                        List<ShouYeBean.DataBean.BrandListBean> brandList = shouYeBean.getData().getBrandList();
                        adapter = new Rec_shouye_zhigongAdapter(brandList, getActivity());
                        rec_shouye_zhigong.setAdapter(adapter);

                        //rec一四首发
                        List<ShouYeBean.DataBean.NewGoodsListBean> newGoodsList = shouYeBean.getData().getNewGoodsList();
                        yisiAdapter = new Rec_shouye_yisiAdapter(newGoodsList, getActivity());
                        yisi.setAdapter(yisiAdapter);
                    }
                });

            }
        });
    }

    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        tabShouye = (TabLayout) view.findViewById(R.id.tab_shouye);
        rec_shouye_zhigong = (RecyclerView) view.findViewById(R.id.rec_shouye_zhigong);
        yisi = (RecyclerView) view.findViewById(R.id.rec_shouye_yisi);
        tvZhigong = (TextView) view.findViewById(R.id.tv_zhigong);
        tvYisi = (TextView) view.findViewById(R.id.tv_yisi);
        rec_shouye_zhigong.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        yisi.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }*/

    @Override
    protected void initData() {
        ((HomePersenter)persenter).getHomeData();
    }

    @Override
    protected IPersenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void HomeDataReturn(final ShouYeBean shouYeBean) {
        List<ShouYeBean.DataBean.BannerBean> banners = shouYeBean.getData().getBanner();

        //banner图片资源的集合
        final ArrayList<String> imgs = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            String image_url = banners.get(i).getImage_url();
            imgs.add(image_url);
        }

        //定义tab集合
        final ArrayList<String> tabs = new ArrayList<>();
        List<ShouYeBean.DataBean.CategoryListBean> categoryList = shouYeBean.getData().getCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            String name = categoryList.get(i).getName();
            tabs.add(name);
        }

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tvYisi.setText("周一周四·新品首发");
                tvZhigong.setText("品牌制造商直供");

                //banner轮播图的实现
                banner.setImages(imgs).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load((String) path).into(imageView);
                    }
                }).start();

                //banner的点击监听
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                //tab的显示
                for (int i = 0; i < tabs.size(); i++) {
                    tabShouye.addTab(tabShouye.newTab().setText(tabs.get(i)));
                }

                //rec直供的适配器
                List<ShouYeBean.DataBean.BrandListBean> brandList = shouYeBean.getData().getBrandList();
                adapter = new Rec_shouye_zhigongAdapter(brandList, getActivity());
                rec_shouye_zhigong.setAdapter(adapter);

                //rec一四首发
                List<ShouYeBean.DataBean.NewGoodsListBean> newGoodsList = shouYeBean.getData().getNewGoodsList();
                yisiAdapter = new Rec_shouye_yisiAdapter(newGoodsList, getActivity());
                yisi.setAdapter(yisiAdapter);
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
