package com.hxb.wan.android.app.constant;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/3/23
 * 描    述：网络地址常量类
 * =============================================
 */
public interface UrlConstants {

    String BASE_URL = "https://www.wanandroid.com";

    //获取公众号列表
    String wxarticle_chapters = "https://wanandroid.com/wxarticle/chapters/json";
    //查看某个公众号历史数据
    String wxarticle_list = "https://wanandroid.com/wxarticle/list/408/1/json";//公众号 ID：拼接在 url 中，eg:405公众号页码：拼接在url 中，eg:1


}
