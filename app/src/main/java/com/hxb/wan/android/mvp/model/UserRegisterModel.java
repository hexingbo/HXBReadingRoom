package com.hxb.wan.android.mvp.model;

import com.google.gson.Gson;
import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.http.ManHuaSeverci;
import com.hxb.wan.android.mvp.model.imodel.IUserRegisterModel;
import com.hxb.wan.android.mvp.view.activity.MainActivity;
import com.hxb.wan.android.mvp.view.activity.UserLoginActivity;
import com.ljy.devring.DevRing;
import com.ljy.devring.util.AppManagerUtil;
import com.ljy.devring.util.DataSPUtils;

import io.reactivex.Observable;

public class UserRegisterModel implements IUserRegisterModel {


    @Override
    public Observable goUserRegister(String username, String password, String repassword) {
        return DevRing.httpManager().getService(ManHuaSeverci.class).goUserRegister(username, password, repassword);
    }

    @Override
    public void jumpMainActivity(UserBean userBean) {
        DataSPUtils.putString(Constants.SP_UserBean, new Gson().toJson(userBean));
        AppManagerUtil.jumpAndFinish(MainActivity.class);
    }

    @Override
    public void jumpUserLoginActivity() {
        AppManagerUtil.jumpAndFinish(UserLoginActivity.class);
    }

}