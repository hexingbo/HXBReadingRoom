package com.hxb.wan.android.mvp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.hxb.wan.android.R;
import com.hxb.wan.android.mvp.presenter.StartPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.iview.IStartView;
import com.ljy.devring.util.AppManagerUtil;


public class StartActivity extends BaseActivity<StartPresenter> implements IStartView {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView(Bundle bundle) {
            
    }

    @Override
    protected void initData(Bundle bundle) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    AppManagerUtil.jumpAndFinish(MainActivity.class);
                }
            },2000);
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
