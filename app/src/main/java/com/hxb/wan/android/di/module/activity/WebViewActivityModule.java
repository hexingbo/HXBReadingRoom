package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.WebViewPresenter;
import com.hxb.wan.android.mvp.view.iview.IWebViewView;
import com.hxb.wan.android.mvp.model.imodel.IWebViewModel;
import com.hxb.wan.android.mvp.model.WebViewModel;


@Module
public class WebViewActivityModule {
    private IWebViewView mIView;

    public WebViewActivityModule(IWebViewView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IWebViewView view) {
        return new ProgresDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    IWebViewView iWebViewView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IWebViewModel iWebViewModel() {
        return new WebViewModel();
    }

    @ActivityScope
    @Provides
    WebViewPresenter provideWebViewPresenter(IWebViewView iView, IWebViewModel iModel) {
        return new WebViewPresenter(iView, iModel);
    }
}