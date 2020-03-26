package com.hxb.wan.android.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.activity.DaggerMainActivityComponent;
import com.hxb.wan.android.di.module.activity.MainActivityModule;
import com.hxb.wan.android.mvp.presenter.MainPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.iview.IMainView;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.public_toolbar_back)
    RelativeLayout publicToolbarBack;
    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;

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
        publicToolbarBack.setVisibility(View.INVISIBLE);
        publicToolbarTitle.setText(R.string.app_name);
    }

    @Override
    protected void initEvent() {

    }

}
