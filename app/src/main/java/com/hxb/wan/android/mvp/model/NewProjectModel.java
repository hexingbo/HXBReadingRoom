package com.hxb.wan.android.mvp.model;

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
}