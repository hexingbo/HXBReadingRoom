package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.ljy.devring.DevRing;

import io.reactivex.Observable;

public class UserLoginModel implements IUserLoginModel {


    @Override
    public Observable goUserLogin(String username, String password) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).goUserLogin(username, password);
    }

}