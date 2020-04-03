package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IMainModel;
import com.hxb.wan.android.mvp.view.activity.UserLoginActivity;
import com.ljy.devring.DevRing;
import com.ljy.devring.util.AppManagerUtil;

import java.util.List;

import io.reactivex.Observable;

public class MainModel implements IMainModel {

    @Override
    public Observable<HttpResult<List<BannerData>>> getBannerList() {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).getBannerList();
    }

    @Override
    public Observable<HttpResult> goLogOutUser() {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).goLogOutUser();
    }

    //更新侧滑栏菜单用户信息
    @Override
    public void clearUserInfo() {
        DevRing.busManager().postEvent(MainDataEvent.init().cleanLoginInfo());
        AppManagerUtil.jump(UserLoginActivity.class);
    }

}