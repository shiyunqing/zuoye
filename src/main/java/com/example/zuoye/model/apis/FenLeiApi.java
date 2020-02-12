package com.example.moni.model.apis;

import com.example.moni.model.bean.FenLeiBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface FenLeiApi {

    @GET("catalog/index")
    Flowable<FenLeiBean> getFenLeiBean();
}
