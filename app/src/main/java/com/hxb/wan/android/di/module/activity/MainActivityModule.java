package com.hxb.wan.android.di.module.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.MainPresenter;
import com.hxb.wan.android.mvp.view.iview.IMainView;
import com.hxb.wan.android.mvp.model.imodel.IMainModel;
import com.hxb.wan.android.mvp.model.MainModel;


@Module
public class MainActivityModule {
    private IMainView mIView;

    public MainActivityModule(IMainView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    IMainView iMainView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IMainModel iMainModel() {
        return new MainModel();
    }

    @ActivityScope
    @Provides
    MainPresenter provideMainPresenter(IMainView iView, IMainModel iModel) {
        return new MainPresenter(iView, iModel);
    }
}