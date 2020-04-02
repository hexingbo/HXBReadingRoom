package com.hxb.wan.android.mvp.view.fragment;

import android.app.Dialog;
import android.support.annotation.NonNull;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.fragment.DaggerNewArticleFragmentComponent;
import com.hxb.wan.android.di.module.fragment.NewArticleFragmentModule;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.presenter.NewArticlePresenter;
import com.hxb.wan.android.mvp.view.adapter.NewArticleAdapter;
import com.hxb.wan.android.mvp.view.fragment.base.BaseFragment;
import com.hxb.wan.android.mvp.view.iview.INewArticleView;
import com.hxb.wan.android.utils.LayoutManagerUtil;
import com.ljy.devring.util.RingToast;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/4/1
 * 描    述： NewArticleFragment 最新文章
 * =============================================
 */
public class NewArticleFragment extends BaseFragment<NewArticlePresenter> implements INewArticleView {

    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;

    @Inject
    NewArticleAdapter mAdapter;
    @Inject
    List<WxArticleDataBean> mDataBeanList;

    @Inject
    Dialog mDialog;


    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void initView() {
        DaggerNewArticleFragmentComponent.builder().newArticleFragmentModule(new NewArticleFragmentModule(this)).build().inject(this);
        LayoutManagerUtil.initView(mRecyclerView, true, new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                mPresenter.getNewArticleTopList();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mPresenter.getNewArticleList();
            }
        }).setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mAdapter.clear();
        mAdapter.setState(HelperStateRecyclerViewAdapter.STATE_LOADING);
        mPresenter.getNewArticleTopList();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showLoading() {
        if (!mRecyclerView.isRefresh() || !mRecyclerView.isLoadingMore()) {
            mDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        RingToast.show(message);
    }

    @Override
    public XRecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public NewArticleAdapter getAdapter() {
        return mAdapter;
    }
}
