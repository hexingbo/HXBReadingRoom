package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleListData;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import java.util.List;

import io.reactivex.Observable;


public interface INewArticleModel extends IBaseModel {

    //获取置顶文章
    Observable<HttpResult<List<WxArticleDataBean>>> getNewArticleTopList();

    //获取最新文章
    Observable<HttpResult<WxArticleListData>> getNewArticleList(int page);

    //添加到我的收藏夹
    Observable<HttpResult> postCollectOrUnCollect(boolean add,int id);

    //更新侧滑栏菜单收藏夹数量
    void updateMenuUserCollectNumber(int number);

}
