package com.hxb.wan.android.mvp.view.activity;

import android.os.Bundle;

import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;

import com.hxb.wan.android.mvp.view.iview.IUserRegisterView;
import com.hxb.wan.android.mvp.presenter.UserRegisterPresenter;

import com.hxb.wan.android.R;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/3/26
 * 描    述：用户注册
 * =============================================
 */
public class UserRegisterActivity extends BaseActivity<UserRegisterPresenter> implements IUserRegisterView {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_user_register;
    }

    @Override
    protected void initView(Bundle bundle) {

    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initEvent() {

    }

}
