package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import io.reactivex.Observable;


public interface IUserLoginModel extends IBaseModel {

    Observable goUserLogin(String username, String password);

}