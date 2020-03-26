package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface IUserRegisterModel extends IBaseModel {

    Observable goUserRegister(String username, String password, String repassword);

    void jumpMainActivity(UserBean userBean);

    void jumpUserLoginActivity();

}
