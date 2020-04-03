package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;
import android.os.Bundle;

import com.hxb.wan.android.mvp.model.MainModel;
import com.hxb.wan.android.mvp.model.imodel.IMainModel;
import com.hxb.wan.android.mvp.presenter.MainPresenter;
import com.hxb.wan.android.mvp.view.fragment.NewArticleFragment;
import com.hxb.wan.android.mvp.view.fragment.NewProjectFragment;
import com.hxb.wan.android.mvp.view.iview.IMainView;
import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class MainActivityModule {
    private IMainView mIView;

    public MainActivityModule(IMainView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IMainView view) {
        return new ProgresDialog(view.getActivity());
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

    @ActivityScope
    @Provides
    @Named("NewArticle")
    NewArticleFragment newArticleFragment() {
        Bundle bundle = new Bundle();
//        bundle.putInt("type", NewArticleFragment.TYPE_PLAYING);
        NewArticleFragment fragment = new NewArticleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @ActivityScope
    @Provides
    @Named("NewProject")
    NewProjectFragment newProjectFragment() {
        Bundle bundle = new Bundle();
//        bundle.putInt("type", NewProjectFragment.TYPE_COMMING);
        NewProjectFragment fragment = new NewProjectFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}