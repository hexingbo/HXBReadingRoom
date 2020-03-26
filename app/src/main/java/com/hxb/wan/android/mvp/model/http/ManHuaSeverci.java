package com.hxb.wan.android.mvp.model.http;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @Author :hexingbo
 * @Date :2020/3/23
 * @FileName： ManHuaSeverci
 * @Describe :
 */
public interface ManHuaSeverci {
    //请求参数一次性传入（通过Map来存放参数名和参数值）
    @GET
    Observable<HttpResult> getSeachManHuaNameList(@Query("mhname") String mhname);
}
