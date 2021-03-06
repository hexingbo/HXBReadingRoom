package com.hxb.wan.android.di.module.fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.mvp.model.NewArticleModel;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.imodel.INewArticleModel;
import com.hxb.wan.android.mvp.presenter.NewArticlePresenter;
import com.hxb.wan.android.mvp.view.adapter.NewArticleAdapter;
import com.hxb.wan.android.mvp.view.iview.INewArticleView;
import com.hxb.wan.android.mvp.view.weight.ProgresDialog;
import com.ljy.devring.di.scope.FragmentScope;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;


@Module
public class NewArticleFragmentModule {
    private INewArticleView mIView;

    public NewArticleFragmentModule(INewArticleView iView) {
        mIView = iView;
    }

    @FragmentScope
    @Provides
    static Dialog provideDialog(INewArticleView view) {
        return new ProgresDialog(view.getActivity());
    }

    @FragmentScope
    @Provides
    INewArticleView iNewArticleView() {
        return mIView;
    }

    @FragmentScope
    @Provides
    INewArticleModel iNewArticleModel() {
        return new NewArticleModel();
    }

    @FragmentScope
    @Provides
    NewArticlePresenter provideNewArticlePresenter(INewArticleView iView, INewArticleModel iModel) {
        return new NewArticlePresenter(iView, iModel);
    }

    @FragmentScope
    @Provides
    static List<WxArticleDataBean> provideList() {
        return new ArrayList<WxArticleDataBean>();
    }

    @FragmentScope
    @Provides
    NewArticleAdapter provideNewArticleAdapter(List<WxArticleDataBean> list, INewArticleView iView) {
        NewArticleAdapter mAdapter = new NewArticleAdapter(list, iView.getActivity());
        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<WxArticleDataBean>() {
            @Override
            public void onItemClick(View view, WxArticleDataBean item, int position) {
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