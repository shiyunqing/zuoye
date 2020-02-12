package com.example.moni.percenter;

import com.example.moni.Utils.RxUtils;
import com.example.moni.base.BasePersenter;
import com.example.moni.interfaces.home.HomeContract;
import com.example.moni.model.CommonSubscriber;
import com.example.moni.model.bean.ShouYeBean;
import com.example.moni.model.http.HttpManager;

public class HomePersenter extends BasePersenter<HomeContract.View> implements HomeContract.Persenter{

    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getHomeApi().getShouYeBean()
                .compose(RxUtils.<ShouYeBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ShouYeBean>(mView){
                    @Override
                    public void onNext(ShouYeBean shouYeBean) {
                        mView.HomeDataReturn(shouYeBean);
                    }
                }));
    }
}
