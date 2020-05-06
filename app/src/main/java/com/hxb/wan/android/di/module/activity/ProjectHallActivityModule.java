package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.presenter.ProjectHallPresenter;
import com.hxb.wan.android.mvp.view.iview.IProjectHallView;
import com.hxb.wan.android.mvp.model.imodel.IProjectHallModel;
import com.hxb.wan.android.mvp.model.ProjectHallModel;


@Module
public class ProjectHallActivityModule {
    private IProjectHallView mIView;

    public ProjectHallActivityModule(IProjectHallView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IProjectHallView view) {
        return new ProgresDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    IProjectHallView iProjectHallView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IProjectHallModel iProjectHallModel() {
        return new ProjectHallModel();
    }

    @ActivityScope
    @Provides
    ProjectHallPresenter provideProjectHallPresenter(IProjectHallView iView, IProjectHallModel iModel) {
        return new ProjectHallPresenter(iView, iModel);
    }
}