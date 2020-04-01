package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IMainModel;
import com.ljy.devring.DevRing;

import java.util.List;

import io.reactivex.Observable;

public class MainModel implements IMainModel {

    @Override
    public Observable<HttpResult<List<BannerData>>> getBannerList() {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).getBannerList();
    }
}