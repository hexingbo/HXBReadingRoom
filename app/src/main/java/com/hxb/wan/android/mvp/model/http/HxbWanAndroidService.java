package com.hxb.wan.android.mvp.model.http;

import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectListData;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleListData;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectListData;

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
 * @FileName： HxbWanAndroidService
 * @Describe :
 */
public interface HxbWanAndroidService {
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
     * 退出登录
     *
     * @return
     */
    @GET("user/logout/json")
    Observable<HttpResult> goLogOutUser();


    /**
     * 获取广告栏数据
     *
     * @return
     */
    @GET("banner/json")
    Observable<HttpResult<List<BannerData>>> getBannerList();

    /**
     * 获取置顶文章
     *
     * @return
     */
    @GET("article/top/json")
    Observable<HttpResult<List<WxArticleDataBean>>> getWxArticleTopList();

    /**
     * 获取feed文章列表
     *
     * @param page 页数
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<HttpResult<WxArticleListData>> getWxArticleList(@Path("page") int page);

    /**
     * 获取最新项目列表
     *
     * @param page 页数
     * @return
     */
    @GET("article/listproject/{page}/json")
    Observable<HttpResult<WxProjectListData>> getWxPorjectList(@Path("page") int page);


    /**
     * 获取最新项目列表
     *
     * @param page 页数
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<HttpResult<MyCollectListData>> getMyCollectedList(@Path("page") int page);

    /**
     * 收藏站内文章
     *
     * @param id 文章id，
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<HttpResult> postCollect(@Path("id") int id);

    /**
     * 取消收藏(文章列表里面的)
     *
     * @param originId 文章id，
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<HttpResult> postUncollectOriginId(@Path("id") int originId);

    /**
     * 取消收藏(我的收藏页面)
     *
     * @param id       文章id，
     * @param originId originId:列表页下发，无则为-1
     * @return
     */
    @FormUrlEncoded
    @POST("lg/uncollect/{id}/json")
    Observable<HttpResult> postUncollect(@Path("id") int id, @Field("originId") int originId);


}
