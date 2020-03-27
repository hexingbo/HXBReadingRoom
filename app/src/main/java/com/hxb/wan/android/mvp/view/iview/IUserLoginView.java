package com.hxb.wan.android.mvp.view.iview;

import android.app.Activity;

import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.view.iview.base.IBaseView;


public interface IUserLoginView extends IBaseView {

    Activity getActivity();
    
    void jumpMainActivity(UserBean userBean);

    void jumpUserRegisterActivity();
    
}
