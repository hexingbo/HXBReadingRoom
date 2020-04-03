package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectListData;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface INewProjectModel extends IBaseModel {

    //获取最新项目
    Observable<HttpResult<WxProjectListData>> getNewProjectList(int page);

}
