package com.hxb.reading.room.di.module.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.reading.room.mvp.presenter.MainPresenter;
import com.hxb.reading.room.mvp.view.iview.IMainView;
import com.hxb.reading.room.mvp.model.imodel.IMainModel;
import com.hxb.reading.room.mvp.model.MainModel;


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