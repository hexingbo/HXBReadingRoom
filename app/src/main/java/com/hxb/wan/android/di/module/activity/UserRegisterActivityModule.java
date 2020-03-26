package com.hxb.wan.android.di.module.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.UserRegisterPresenter;
import com.hxb.wan.android.mvp.view.iview.IUserRegisterView;
import com.hxb.wan.android.mvp.model.imodel.IUserRegisterModel;
import com.hxb.wan.android.mvp.model.UserRegisterModel;


@Module
public class UserRegisterActivityModule {
    private IUserRegisterView mIView;

    public UserRegisterActivityModule(IUserRegisterView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    IUserRegisterView iUserRegisterView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IUserRegisterModel iUserRegisterModel() {
        return new UserRegisterModel();
    }

    @ActivityScope
    @Provides
    UserRegisterPresenter provideUserRegisterPresenter(IUserRegisterView iView, IUserRegisterModel iModel) {
        return new UserRegisterPresenter(iView, iModel);
    }
}