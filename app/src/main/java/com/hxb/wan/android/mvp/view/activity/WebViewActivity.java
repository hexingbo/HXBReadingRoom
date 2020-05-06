package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;

import com.hxb.wan.android.mvp.view.iview.IWebViewView;
import com.hxb.wan.android.mvp.presenter.WebViewPresenter;

import com.hxb.wan.android.R;
import com.ljy.devring.util.RingToast;

import javax.inject.Inject;


public class WebViewActivity extends BaseActivity<WebViewPresenter> implements IWebViewView {

    @Inject
    Dialog mDialog;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView(Bundle bundle) {
        //  DaggerWebViewActivityComponent.builder().WebViewActivityModule(new WebViewActivityModule(this)).build().inject(this);
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
