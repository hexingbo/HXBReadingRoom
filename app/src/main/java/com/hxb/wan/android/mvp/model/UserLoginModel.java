package com.hxb.wan.android.mvp.model;

import com.google.gson.Gson;
import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.ljy.devring.DevRing;
import com.ljy.devring.util.AppManagerUtil;
import com.ljy.devring.util.DataSPUtils;

import io.reactivex.Observable;

public class UserLoginModel implements IUserLoginModel {


    @Override
    public Observable goUserLogin(String username, String password) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).goUserLogin(username, password);
    }

    //更新侧滑栏菜单用户信息
    @Override
    public void updateMenuUserInfo(UserBean userBean) {
        DataSPUtils.putString(Constants.SP_UserBean, userBean != null ? new Gson().toJson(userBean) : "");
        DevRing.busManager().postEvent(new MainDataEvent(userBean != null));
        AppManagerUtil.getCurrentActivity().finish();
    }

}