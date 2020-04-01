package com.hxb.wan.android.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.hxb.wan.android.mvp.view.fragment.base.BaseFragment;
import com.hxb.wan.android.mvp.view.iview.INewProjectView;
import com.hxb.wan.android.mvp.presenter.NewProjectPresenter;
import com.hxb.wan.android.R;

import android.support.annotation.NonNull;


public class NewProjectFragment extends BaseFragment<NewProjectPresenter> implements INewProjectView {

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_new_project;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
