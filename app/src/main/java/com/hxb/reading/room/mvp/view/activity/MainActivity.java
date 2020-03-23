package com.hxb.reading.room.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxb.reading.room.R;
import com.hxb.reading.room.di.component.activity.DaggerMainActivityComponent;
import com.hxb.reading.room.di.module.activity.MainActivityModule;
import com.hxb.reading.room.mvp.presenter.MainPresenter;
import com.hxb.reading.room.mvp.view.activity.base.BaseActivity;
import com.hxb.reading.room.mvp.view.iview.IMainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
        publicToolbarTitle.setText("MainActivity");
    }

    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.tv_manhua)
    public void onTvManhuaClicked() {
        //漫画走廊
    }

    @OnClick(R.id.tv_xiaoshuo)
    public void onTvXiaoshuoClicked() {
        //小说天地
    }
}
