package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.PublicAccountPresenter;
import com.hxb.wan.android.mvp.view.iview.IPublicAccountView;
import com.hxb.wan.android.mvp.model.imodel.IPublicAccountModel;
import com.hxb.wan.android.mvp.model.PublicAccountModel;


@Module
public class PublicAccountActivityModule {
    private IPublicAccountView mIView;

    public PublicAccountActivityModule(IPublicAccountView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IPublicAccountView view) {
        return new ProgresDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    IPublicAccountView iPublicAccountView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IPublicAccountModel iPublicAccountModel() {
        return new PublicAccountModel();
    }

    @ActivityScope
    @Provides
    PublicAccountPresenter providePublicAccountPresenter(IPublicAccountView iView, IPublicAccountModel iModel) {
        return new PublicAccountPresenter(iView, iModel);
    }
}