package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface IUserLoginModel extends IBaseModel {

    Observable goUserLogin(String username, String password);

    //更新侧滑栏菜单用户信息
    void updateMenuUserInfo(UserBean userBean);

}
