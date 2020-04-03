package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectListData;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface INewProjectModel extends IBaseModel {

    //获取最新项目
    Observable<HttpResult<WxProjectListData>> getNewProjectList(int page);

    //添加到我的收藏夹
    Observable<HttpResult> postCollect(int id);
    
    //更新侧滑栏菜单收藏夹数量
    void updateMenuUserCollectNumber(int number);
    
}
