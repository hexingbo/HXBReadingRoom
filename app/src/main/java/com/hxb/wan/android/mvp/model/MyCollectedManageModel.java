package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.event.UncollectArticleEvent;
import com.hxb.wan.android.mvp.model.entity.event.UserCollectEm;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectListData;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IMyCollectedManageModel;
import com.ljy.devring.DevRing;

import org.greenrobot.greendao.annotation.Id;

import io.reactivex.Observable;

public class MyCollectedManageModel implements IMyCollectedManageModel {


    @Override
    public Observable<HttpResult<MyCollectListData>> getMyCollectedList(int page) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).getMyCollectedList(page);
    }

    @Override
    public Observable<HttpResult> postUnCollect(int id, int originId ) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).postUncollect(id,originId);
    }

    @Override
    public void updateMenuUserCollectNumber(int number,int originId) {
        DevRing.busManager().postEvent(MainDataEvent.init().setUserCollectedNumber(number));
        DevRing.busManager().postEvent(new UncollectArticleEvent(originId,false, UserCollectEm.CollectedManage));
    }


}