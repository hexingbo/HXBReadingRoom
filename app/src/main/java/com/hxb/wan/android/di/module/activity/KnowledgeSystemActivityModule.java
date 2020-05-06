package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.KnowledgeSystemPresenter;
import com.hxb.wan.android.mvp.view.iview.IKnowledgeSystemView;
import com.hxb.wan.android.mvp.model.imodel.IKnowledgeSystemModel;
import com.hxb.wan.android.mvp.model.KnowledgeSystemModel;


@Module
public class KnowledgeSystemActivityModule {
    private IKnowledgeSystemView mIView;

    public KnowledgeSystemActivityModule(IKnowledgeSystemView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IKnowledgeSystemView view) {
        return new ProgresDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    IKnowledgeSystemView iKnowledgeSystemView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IKnowledgeSystemModel iKnowledgeSystemModel() {
        return new KnowledgeSystemModel();
    }

    @ActivityScope
    @Provides
    KnowledgeSystemPresenter provideKnowledgeSystemPresenter(IKnowledgeSystemView iView, IKnowledgeSystemModel iModel) {
        return new KnowledgeSystemPresenter(iView, iModel);
    }
}