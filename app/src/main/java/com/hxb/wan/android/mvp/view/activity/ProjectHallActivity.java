package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;

import com.hxb.wan.android.mvp.view.iview.IProjectHallView;
import com.hxb.wan.android.mvp.presenter.ProjectHallPresenter;

import com.hxb.wan.android.R;
import com.ljy.devring.util.RingToast;

import javax.inject.Inject;

/**
 * 项目大厅
 */
public class ProjectHallActivity extends BaseActivity<ProjectHallPresenter> implements IProjectHallView {

    @Inject
    Dialog mDialog;

    @Override
    protected int getContentLayout() {
        return R.layout.public_layout_rcy_title;
    }

    @Override
    protected void initView(Bundle bundle) {
        //  DaggerProjectHallActivityComponent.builder().ProjectHallActivityModule(new ProjectHallActivityModule(this)).build().inject(this);
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
