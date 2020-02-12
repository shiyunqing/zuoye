package com.example.moni.interfaces.fenlei;

import com.example.moni.interfaces.IBaseView;
import com.example.moni.interfaces.IPersenter;
import com.example.moni.model.bean.FenLeiBean;
import com.example.moni.model.bean.ShouYeBean;

public interface FenLeiContract {
    interface View extends IBaseView{
        void FenLeiDataReturn(FenLeiBean fenLeiBean);
    }

    interface Persenter extends IPersenter<View>{
        void getFenLeiData();
    }
}
