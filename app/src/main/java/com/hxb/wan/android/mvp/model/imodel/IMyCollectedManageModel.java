package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectListData;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface IMyCollectedManageModel extends IBaseModel {

    //获取最新文章
    Observable<HttpResult<MyCollectListData>> getMyCollectedList(int page);

    //从我的收藏夹移除文章
    Observable<HttpResult> postUnCollect(int id,int originId);

    //更新侧滑栏菜单收藏夹数量
    void updateMenuUserCollectNumber(int number,int originId);

}
