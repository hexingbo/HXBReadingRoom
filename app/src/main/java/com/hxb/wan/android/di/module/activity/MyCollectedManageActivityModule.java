package com.hxb.wan.android.di.module.activity;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.mvp.model.MyCollectedManageModel;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectedBean;
import com.hxb.wan.android.mvp.model.imodel.IMyCollectedManageModel;
import com.hxb.wan.android.mvp.presenter.MyCollectedManagePresenter;
import com.hxb.wan.android.mvp.view.adapter.MyCollectedAdapter;
import com.hxb.wan.android.mvp.view.iview.IMyCollectedManageView;
import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.ActivityScope;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

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
        MyCollectedAdapter mAdapter = new MyCollectedAdapter(list, iView.getActivity());
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<MyCollectedBean>() {
            @Override
            public void onItemClick(View view, MyCollectedBean item, int position) {
                if (view.getId() == R.id.ll_error_view) {
                    //请求失败刷新数据
                    mAdapter.clear();
                    mAdapter.setState(HelperStateRecyclerViewAdapter.STATE_LOADING);
                    iView.getRecyclerView().refresh();
                } else if (view.getId() == R.id.img_shoucang) {
                    mIView.onItemShouCangClick((ImageView) view, item, position);
                } else {
                    mIView.onItemClick(item, position);
                }
            }
        });
        return mAdapter;
    }
}