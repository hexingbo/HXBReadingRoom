package com.hxb.wan.android.di.module.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.UserLoginPresenter;
import com.hxb.wan.android.mvp.view.iview.IUserLoginView;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.hxb.wan.android.mvp.model.UserLoginModel;


@Module
public class UserLoginActivityModule {
    private IUserLoginView mIView;

    public UserLoginActivityModule(IUserLoginView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    IUserLoginView iUserLoginView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IUserLoginModel iUserLoginModel() {
        return new UserLoginModel();
    }

    @ActivityScope
    @Provides
    UserLoginPresenter provideUserLoginPresenter(IUserLoginView iView, IUserLoginModel iModel) {
        return new UserLoginPresenter(iView, iModel);
    }
}