package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.NavigateMenuPresenter;
import com.hxb.wan.android.mvp.view.iview.INavigateMenuView;
import com.hxb.wan.android.mvp.model.imodel.INavigateMenuModel;
import com.hxb.wan.android.mvp.model.NavigateMenuModel;


@Module
public class NavigateMenuActivityModule {
    private INavigateMenuView mIView;

    public NavigateMenuActivityModule(INavigateMenuView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(INavigateMenuView view) {
        return new ProgresDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    INavigateMenuView iNavigateMenuView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    INavigateMenuModel iNavigateMenuModel() {
        return new NavigateMenuModel();
    }

    @ActivityScope
    @Provides
    NavigateMenuPresenter provideNavigateMenuPresenter(INavigateMenuView iView, INavigateMenuModel iModel) {
        return new NavigateMenuPresenter(iView, iModel);
    }
}