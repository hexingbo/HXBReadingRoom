package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectListData;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.INewProjectModel;
import com.ljy.devring.DevRing;

import io.reactivex.Observable;

public class NewProjectModel implements INewProjectModel {


    @Override
    public Observable<HttpResult<WxProjectListData>> getNewProjectList(int page) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).getWxPorjectList(page);
    }

    @Override
    public Observable<HttpResult> postCollect(int id) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).postCollect(id);
    }

    @Override
    public void updateMenuUserCollectNumber(int number) {
        DevRing.busManager().postEvent(MainDataEvent.init().setUserCollectedNumber(number));
    }

}