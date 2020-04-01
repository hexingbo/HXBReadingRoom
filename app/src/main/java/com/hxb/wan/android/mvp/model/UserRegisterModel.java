package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IUserRegisterModel;
import com.ljy.devring.DevRing;

import io.reactivex.Observable;

public class UserRegisterModel implements IUserRegisterModel {


    @Override
    public Observable goUserRegister(String username, String password, String repassword) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).goUserRegister(username, password, repassword);
    }

}