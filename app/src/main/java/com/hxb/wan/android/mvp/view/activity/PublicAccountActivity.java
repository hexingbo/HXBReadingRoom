package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;

import com.hxb.wan.android.mvp.view.iview.IPublicAccountView;
import com.hxb.wan.android.mvp.presenter.PublicAccountPresenter;

import com.hxb.wan.android.R;
import com.ljy.devring.util.RingToast;

import javax.inject.Inject;

/**
 * 公众号
 */
public class PublicAccountActivity extends BaseActivity<PublicAccountPresenter> implements IPublicAccountView {

    @Inject
    Dialog mDialog;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_public_account;
    }

    @Override
    protected void initView(Bundle bundle) {
        //  DaggerPublicAccountActivityComponent.builder().PublicAccountActivityModule(new PublicAccountActivityModule(this)).build().inject(this);
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
