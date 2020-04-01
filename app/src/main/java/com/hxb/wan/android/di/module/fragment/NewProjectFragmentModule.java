package com.hxb.wan.android.di.module.fragment;

import com.hxb.wan.android.mvp.presenter.NewProjectPresenter;
import com.ljy.devring.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.hxb.wan.android.mvp.view.iview.INewProjectView;
import com.hxb.wan.android.mvp.model.imodel.INewProjectModel;
import com.hxb.wan.android.mvp.model.NewProjectModel;
import com.hxb.wan.android.mvp.view.weight.ProgresDialog;

import android.app.Dialog;


@Module
public class NewProjectFragmentModule {
    private INewProjectView mIView;

    public NewProjectFragmentModule(INewProjectView iView) {
        mIView = iView;
    }

    @FragmentScope
    @Provides
    static Dialog provideDialog(INewProjectView view) {
        return new ProgresDialog(view.getActivity());
    }

    @FragmentScope
    @Provides
    INewProjectView iNewProjectView() {
        return mIView;
    }

    @FragmentScope
    @Provides
    INewProjectModel iNewProjectModel() {
        return new NewProjectModel();
    }

    @FragmentScope
    @Provides
    NewProjectPresenter provideNewProjectPresenter(INewProjectView iView, INewProjectModel iModel) {
        return new NewProjectPresenter(iView, iModel);
    }
}