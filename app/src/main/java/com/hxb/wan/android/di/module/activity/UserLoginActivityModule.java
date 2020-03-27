package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.model.UserLoginModel;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.hxb.wan.android.mvp.presenter.UserLoginPresenter;
import com.hxb.wan.android.mvp.view.iview.IUserLoginView;
import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;


@Module
public class UserLoginActivityModule {
    private IUserLoginView mIView;

    public UserLoginActivityModule(IUserLoginView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IUserLoginView view){
        return new ProgresDialog(view.getActivity());
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