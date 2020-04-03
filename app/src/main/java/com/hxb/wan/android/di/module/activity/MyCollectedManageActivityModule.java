package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;

import com.hxb.wan.android.mvp.model.MyCollectedManageModel;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectedBean;
import com.hxb.wan.android.mvp.model.imodel.IMyCollectedManageModel;
import com.hxb.wan.android.mvp.presenter.MyCollectedManagePresenter;
import com.hxb.wan.android.mvp.view.adapter.MyCollectedAdapter;
import com.hxb.wan.android.mvp.view.iview.IMyCollectedManageView;
import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class MyCollectedManageActivityModule {
    private IMyCollectedManageView mIView;

    public MyCollectedManageActivityModule(IMyCollectedManageView iView) {
        mIView = iView;
    }

    @ActivityScope
    @Provides
    static Dialog provideDialog(IMyCollectedManageView view) {
        return new ProgresDialog(view.getActivity());
    }

    @ActivityScope
    @Provides
    IMyCollectedManageView iMyCollectedManageView() {
        return mIView;
    }

    @ActivityScope
    @Provides
    IMyCollectedManageModel iMyCollectedManageModel() {
        return new MyCollectedManageModel();
    }

    @ActivityScope
    @Provides
    MyCollectedManagePresenter provideMyCollectedManagePresenter(IMyCollectedManageView iView, IMyCollectedManageModel iModel) {
        return new MyCollectedManagePresenter(iView, iModel);
    }

    @ActivityScope
    @Provides
    static List<MyCollectedBean> provideList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    MyCollectedAdapter provideMyCollectedAdapter(List<MyCollectedBean> list, IMyCollectedManageView iView) {
        return new MyCollectedAdapter(list, iView.getActivity());
    }
}