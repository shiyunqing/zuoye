package com.example.moni.model.apis;

import com.example.moni.model.bean.ShouYeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface HomeApi {

    @GET("api/index/index")
    Flowable<ShouYeBean> getShouYeBean();
}
