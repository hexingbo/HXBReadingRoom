package com.hxb.wan.android.mvp.model.http;

import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
    @POST("user/login")
    Observable<HttpResult<UserBean>> goUserLogin(@Field("username") String username, @Field("password") String password);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param repassword
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<HttpResult<UserBean>> goUserRegister(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 获取feed文章列表
     *
     * @param number 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{number}/json")
    Observable<HttpResult<WxArticleListBean>> getWxArticleList(@Path("number") int number);

    /**
     * 广告栏
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<HttpResult<List<BannerData>>> getBanner();


}
