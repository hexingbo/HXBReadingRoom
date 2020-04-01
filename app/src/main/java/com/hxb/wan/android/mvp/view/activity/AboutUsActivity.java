package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.hxb.wan.android.di.component.activity.DaggerAboutUsActivityComponent;
import com.hxb.wan.android.di.module.activity.AboutUsActivityModule;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;

import com.hxb.wan.android.mvp.view.iview.IAboutUsView;
import com.hxb.wan.android.mvp.presenter.AboutUsPresenter;

import com.hxb.wan.android.R;
import com.ljy.devring.util.RingToast;

import javax.inject.Inject;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/3/27
 * 描    述：关于我们
 * =============================================
 */
public class AboutUsActivity extends BaseActivity<AboutUsPresenter> implements IAboutUsView {

    @Inject
    Dialog mDialog;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView(Bundle bundle) {
          DaggerAboutUsActivityComponent.builder().aboutUsActivityModule(new AboutUsActivityModule(this)).build().inject(this);
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
