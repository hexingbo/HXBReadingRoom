package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.AboutUsPresenter;
import com.hxb.wan.android.mvp.view.iview.IAboutUsView;
import com.hxb.wan.android.mvp.model.imodel.IAboutUsModel;
import com.hxb.wan.android.mvp.model.AboutUsModel;


@Module
public class AboutUsActivityModule {
    private IAboutUsView mIView;

    public AboutUsActivityModule(IAboutUsView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IAboutUsView view) {
        return new ProgresDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    IAboutUsView iAboutUsView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IAboutUsModel iAboutUsModel() {
        return new AboutUsModel();
    }

    @ActivityScope
    @Provides
    AboutUsPresenter provideAboutUsPresenter(IAboutUsView iView, IAboutUsModel iModel) {
        return new AboutUsPresenter(iView, iModel);
    }
}