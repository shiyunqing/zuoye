package com.example.moni.percenter;

import com.example.moni.Utils.RxUtils;
import com.example.moni.base.BasePersenter;
import com.example.moni.interfaces.fenleiitem.FenLeiItemContract;
import com.example.moni.model.CommonSubscriber;
import com.example.moni.model.bean.FenLei_TabBean;
import com.example.moni.model.http.HttpManager;

public class FenLeiItemPersenter extends BasePersenter<FenLeiItemContract.View> implements FenLeiItemContract.Persenter {
    @Override
    public void getFenLeiItemData(int id) {
        addSubscribe(HttpManager.getFenLeiItemApi().getFenLeitemBean(id)
                .compose(RxUtils.<FenLei_TabBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<FenLei_TabBean>(mView){
                    @Override
                    public void onNext(FenLei_TabBean fenLei_tabBean) {
                        mView.FenLeiItemDataReturn(fenLei_tabBean);
                    }
                }));
    }
}
