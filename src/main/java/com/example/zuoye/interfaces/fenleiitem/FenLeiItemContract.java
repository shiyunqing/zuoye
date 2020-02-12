package com.example.moni.interfaces.fenleiitem;

import com.example.moni.interfaces.IBaseView;
import com.example.moni.interfaces.IPersenter;
import com.example.moni.model.bean.FenLeiBean;
import com.example.moni.model.bean.FenLei_TabBean;

public interface FenLeiItemContract {
    interface View extends IBaseView{
        void FenLeiItemDataReturn(FenLei_TabBean fenLei_tabBean);
    }

    interface Persenter extends IPersenter<View>{
        void getFenLeiItemData(int id);
    }
}
