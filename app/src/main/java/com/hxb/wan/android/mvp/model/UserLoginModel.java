package com.hxb.wan.android.mvp.model;

import com.google.gson.Gson;
import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.http.ManHuaSeverci;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.hxb.wan.android.mvp.view.activity.MainActivity;
import com.hxb.wan.android.mvp.view.activity.UserRegisterActivity;
import com.ljy.devring.DevRing;
import com.ljy.devring.util.AppManagerUtil;
import com.ljy.devring.util.DataSPUtils;

import io.reactivex.Observable;

public class UserLoginModel implements IUserLoginModel {


    @Override
    public Observable goUserLogin(String username, String password) {
        return DevRing.httpManager().getService(ManHuaSeverci.class).goUserLogin(username, password);
    }

    @Override
    public void jumpMainActivity(UserBean userBean) {
        DataSPUtils.putString(Constants.SP_UserBean, new Gson().toJson(userBean));
        AppManagerUtil.jumpAndFinish(MainActivity.class);
    }

    @Override
    public void jumpUserRegisterActivity() {
        AppManagerUtil.jumpAndFinish(UserRegisterActivity.class);
    }
}