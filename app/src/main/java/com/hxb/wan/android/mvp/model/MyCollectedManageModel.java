package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectListData;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IMyCollectedManageModel;
import com.ljy.devring.DevRing;

import io.reactivex.Observable;

public class MyCollectedManageModel implements IMyCollectedManageModel {


    @Override
    public Observable<HttpResult<MyCollectListData>> getMyCollectedList(int page) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).getMyCollectedList(page);
    }
}