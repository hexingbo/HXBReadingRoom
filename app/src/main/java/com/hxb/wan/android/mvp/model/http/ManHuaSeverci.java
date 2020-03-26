package com.hxb.wan.android.mvp.model.http;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/user/login")
    Observable<HttpResult<UserBean>> goUserLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/user/register")
    Observable<HttpResult<UserBean>> goUserRegister(@Field("username")String username,@Field("password") String password,@Field("repassword") String repassword);
}
