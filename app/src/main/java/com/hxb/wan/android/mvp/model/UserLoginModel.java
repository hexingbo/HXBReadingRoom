package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.ljy.devring.DevRing;
import com.ljy.devring.util.AppManagerUtil;

import io.reactivex.Observable;

public class UserLoginModel implements IUserLoginModel {


    @Override
    public Observable goUserLogin(String username, String password) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).goUserLogin(username, password);
    }

    //更新侧滑栏菜单用户信息
    @Override
    public void updateMenuUserInfo(UserBean userBean) {
        if (userBean != null)
            DevRing.busManager().postEvent(MainDataEvent.init().saveLoginInfo(userBean.getNickname(), userBean.getIcon(), userBean.getCollectIds().size()));
        else {
            DevRing.busManager().postEvent(MainDataEvent.init().saveLoginInfo("用户昵称", "", 0));
        }
        AppManagerUtil.getCurrentActivity().finish();
    }

}