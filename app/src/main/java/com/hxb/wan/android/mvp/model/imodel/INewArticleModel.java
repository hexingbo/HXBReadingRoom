package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleListBean;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import java.util.List;

import io.reactivex.Observable;


public interface INewArticleModel extends IBaseModel {

    //获取置顶文章
    Observable<HttpResult<List<WxArticleDataBean>>> getNewArticleTopList();

    //获取最新文章
    Observable<HttpResult<WxArticleListBean>> getNewArticleList(int page);

}
