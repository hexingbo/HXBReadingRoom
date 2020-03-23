package com.hxb.reading.room.mvp.view.activity;

import android.os.Bundle;


import com.hxb.reading.room.di.component.activity.DaggerMainActivityComponent;
import com.hxb.reading.room.di.module.activity.MainActivityModule;
import com.hxb.reading.room.mvp.view.activity.base.BaseActivity;
import com.hxb.reading.room.mvp.view.iview.IMainView;
import com.hxb.reading.room.mvp.presenter.MainPresenter;

import com.hxb.reading.room.R;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle bundle) {
        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build().inject(this);

    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initEvent() {

    }

}
