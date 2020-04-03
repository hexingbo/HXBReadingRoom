package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectListData;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface IMyCollectedManageModel extends IBaseModel {

    //获取最新文章
    Observable<HttpResult<MyCollectListData>> getMyCollectedList(int page);

}
