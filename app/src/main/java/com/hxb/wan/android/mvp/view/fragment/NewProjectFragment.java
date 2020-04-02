package com.hxb.wan.android.mvp.view.fragment;


import android.app.Dialog;
import android.support.annotation.NonNull;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.fragment.DaggerNewProjectFragmentComponent;
import com.hxb.wan.android.di.module.fragment.NewProjectFragmentModule;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectDataBean;
import com.hxb.wan.android.mvp.presenter.NewProjectPresenter;
import com.hxb.wan.android.mvp.view.adapter.NewProjectAdapter;
import com.hxb.wan.android.mvp.view.fragment.base.BaseFragment;
import com.hxb.wan.android.mvp.view.iview.INewProjectView;
import com.hxb.wan.android.utils.LayoutManagerUtil;
import com.ljy.devring.util.RingToast;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/4/1
 * 描    述：NewProjectFragment 最新项目
 * =============================================
 */
public class NewProjectFragment extends BaseFragment<NewProjectPresenter> implements INewProjectView {

    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;

    @Inject
    NewProjectAdapter mAdapter;
    @Inject
    List<WxProjectDataBean> mDataBeanList;

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
        DaggerNewProjectFragmentComponent.builder().newProjectFragmentModule(new NewProjectFragmentModule(this)).build().inject(this);
        LayoutManagerUtil.initView(mRecyclerView, true, new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                mPresenter.getNewProjectList(true);
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mPresenter.getNewProjectList(false);
            }
        }).setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mAdapter.clear();
        mAdapter.setState(HelperStateRecyclerViewAdapter.STATE_LOADING);
        mPresenter.getNewProjectList(true);
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
    public NewProjectAdapter getAdapter() {
        return mAdapter;
    }
}
