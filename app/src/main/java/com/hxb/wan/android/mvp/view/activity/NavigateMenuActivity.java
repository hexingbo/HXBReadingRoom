package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.hxb.wan.android.di.component.activity.DaggerNavigateMenuActivityComponent;
import com.hxb.wan.android.di.module.activity.NavigateMenuActivityModule;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;

import com.hxb.wan.android.mvp.view.iview.INavigateMenuView;
import com.hxb.wan.android.mvp.presenter.NavigateMenuPresenter;

import com.hxb.wan.android.R;
import com.ljy.devring.util.RingToast;

import javax.inject.Inject;

/**
 * 导航菜单
 */
public class NavigateMenuActivity extends BaseActivity<NavigateMenuPresenter> implements INavigateMenuView {

    public static final String strBundle = "value";

    @Inject
    Dialog mDialog;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_navigate_menu;
    }

    @Override
    protected void initView(Bundle bundle) {
        DaggerNavigateMenuActivityComponent.builder().navigateMenuActivityModule(new NavigateMenuActivityModule(this)).build().inject(this);
        setTitle(getIntent().getStringExtra(strBundle));
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        RingToast.show(message);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
    
    

}
