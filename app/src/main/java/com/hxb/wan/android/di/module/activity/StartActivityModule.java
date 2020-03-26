package com.hxb.wan.android.di.module.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.StartPresenter;
import com.hxb.wan.android.mvp.view.iview.IStartView;
import com.hxb.wan.android.mvp.model.imodel.IStartModel;
import com.hxb.wan.android.mvp.model.StartModel;


@Module
public class StartActivityModule {
    private IStartView mIView;

    public StartActivityModule(IStartView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    IStartView iStartView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IStartModel iStartModel() {
        return new StartModel();
    }

    @ActivityScope
    @Provides
    StartPresenter provideStartPresenter(IStartView iView, IStartModel iModel) {
        return new StartPresenter(iView, iModel);
    }
}