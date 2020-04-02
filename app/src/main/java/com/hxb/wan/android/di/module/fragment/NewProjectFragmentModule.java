package com.hxb.wan.android.di.module.fragment;

import android.app.Dialog;

import com.hxb.wan.android.mvp.model.NewProjectModel;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectDataBean;
import com.hxb.wan.android.mvp.model.imodel.INewProjectModel;
import com.hxb.wan.android.mvp.presenter.NewProjectPresenter;
import com.hxb.wan.android.mvp.view.adapter.NewProjectAdapter;
import com.hxb.wan.android.mvp.view.iview.INewProjectView;
import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.FragmentScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


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

    @FragmentScope
    @Provides
    static List<WxProjectDataBean> provideList() {
        return new ArrayList<>();
    }

    @FragmentScope
    @Provides
    NewProjectAdapter provideNewProjectAdapter(List<WxProjectDataBean> list,INewProjectView iView) {
        return new NewProjectAdapter(list, iView.getActivity());
    }
    
}