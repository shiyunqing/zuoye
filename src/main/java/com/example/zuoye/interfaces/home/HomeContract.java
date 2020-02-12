package com.example.moni.interfaces.home;

import com.example.moni.interfaces.IBaseView;
import com.example.moni.interfaces.IPersenter;
import com.example.moni.model.bean.ShouYeBean;

public interface HomeContract {
    interface View extends IBaseView{
        void HomeDataReturn(ShouYeBean shouYeBean);
    }

    interface Persenter extends IPersenter<View>{
        void getHomeData();
    }
}
