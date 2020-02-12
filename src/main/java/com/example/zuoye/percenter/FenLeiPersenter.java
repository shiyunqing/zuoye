package com.example.moni.percenter;

import com.example.moni.Utils.RxUtils;
import com.example.moni.base.BaseFragment;
import com.example.moni.base.BasePersenter;
import com.example.moni.interfaces.fenlei.FenLeiContract;
import com.example.moni.model.CommonSubscriber;
import com.example.moni.model.bean.FenLeiBean;
import com.example.moni.model.bean.ShouYeBean;
import com.example.moni.model.http.HttpManager;

public class FenLeiPersenter extends BasePersenter<FenLeiContract.View> implements FenLeiContract.Persenter{

    @Override
    public void getFenLeiData() {
        addSubscribe(HttpManager.getFenLeiApi().getFenLeiBean()
                .compose(RxUtils.<FenLeiBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<FenLeiBean>(mView){
                    @Override
                    public void onNext(FenLeiBean fenLeiBean) {
                        mView.FenLeiDataReturn(fenLeiBean);
                    }
                }));
    }
}
