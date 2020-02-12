package com.example.moni;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.moni.Utils.ShowAndHindUtils;
import com.example.moni.fragment.FenLeiFragment;
import com.example.moni.fragment.HomeFragment;
import com.example.moni.fragment.MeFragment;
import com.example.moni.fragment.ShoppingFragment;
import com.example.moni.fragment.ZhuanTiFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar mTool;
    /**
     * 首页
     */
    private RadioButton mTvShouye;
    /**
     * 专题
     */
    private RadioButton mTvZhuanti;
    /**
     * 分类
     */
    private RadioButton mTvFenlei;
    /**
     * 购物车
     */
    private RadioButton mTvGouwuche;
    /**
     * 我的
     */
    private RadioButton mTvWode;
    private TextView mTvToolbar;
    private HomeFragment homeFragment;
    private ZhuanTiFragment zhuanTiFragment;
    private FenLeiFragment fenLeiFragment;
    private ShoppingFragment shoppingFragment;
    private MeFragment meFragment;
    private Fragment old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    private void initFragment() {
        homeFragment = new HomeFragment();
        zhuanTiFragment = new ZhuanTiFragment();
        fenLeiFragment = new FenLeiFragment();
        shoppingFragment = new ShoppingFragment();
        meFragment = new MeFragment();
        old = homeFragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl,homeFragment)
                .add(R.id.fl,zhuanTiFragment)
                .add(R.id.fl,fenLeiFragment)
                .add(R.id.fl,shoppingFragment)
                .add(R.id.fl,meFragment)
                .show(old)
                .hide(fenLeiFragment)
                .hide(shoppingFragment)
                .hide(zhuanTiFragment)
                .hide(meFragment)
                .commit();
        mTvShouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this,homeFragment,old);
                mTvToolbar.setText("仿网易严选");
                mTool.setBackgroundResource(R.drawable.white);
                mTvToolbar.setTextColor(Color.BLACK);
                old = homeFragment;
            }
        });
        mTvZhuanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this,zhuanTiFragment,old);
                mTool.setBackgroundResource(R.drawable.white);
                mTvToolbar.setText("仿网易严选");
                mTvToolbar.setTextColor(Color.BLACK);
                old = zhuanTiFragment;
            }
        });
        mTvFenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this,fenLeiFragment,old);
                mTvToolbar.setText("仿网易严选");
                mTvToolbar.setTextColor(Color.BLACK);
                mTool.setBackgroundResource(R.drawable.white);
                old = fenLeiFragment;
            }
        });
        mTvGouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this,shoppingFragment,old);
                mTvToolbar.setText("仿网易严选");
                mTvToolbar.setTextColor(Color.BLACK);
                mTool.setBackgroundResource(R.drawable.white);
                old = shoppingFragment;
            }
        });
        mTvWode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this,meFragment,old);
                mTvToolbar.setText("我的");
                mTvToolbar.setTextColor(Color.WHITE);
                mTool.setBackgroundResource(R.drawable.bulake);
                old = meFragment;
            }
        });
    }

    private void initView() {
        mTool = (Toolbar) findViewById(R.id.tool);
        setTitle("");
        setSupportActionBar(mTool);
        mTvShouye = (RadioButton) findViewById(R.id.tv_shouye);
        mTvZhuanti = (RadioButton) findViewById(R.id.tv_zhuanti);
        mTvFenlei = (RadioButton) findViewById(R.id.tv_fenlei);
        mTvGouwuche = (RadioButton) findViewById(R.id.tv_gouwuche);
        mTvWode = (RadioButton) findViewById(R.id.tv_wode);
        mTvToolbar = (TextView) findViewById(R.id.tv_toolbar);
    }
}
