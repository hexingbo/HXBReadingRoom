package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface IUserRegisterModel extends IBaseModel {

    Observable goUserRegister(String username, String password, String repassword);


    //更新侧滑栏菜单用户信息
    void updateMenuUserInfo(UserBean userBean);

}
