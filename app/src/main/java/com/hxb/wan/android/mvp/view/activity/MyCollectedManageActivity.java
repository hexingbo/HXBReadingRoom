package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.activity.DaggerMyCollectedManageActivityComponent;
import com.hxb.wan.android.di.module.activity.MyCollectedManageActivityModule;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectedBean;
import com.hxb.wan.android.mvp.presenter.MyCollectedManagePresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.adapter.MyCollectedAdapter;
import com.hxb.wan.android.mvp.view.iview.IMyCollectedManageView;
import com.hxb.wan.android.utils.LayoutManagerUtil;
import com.ljy.devring.util.RingToast;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/4/3
 * 描    述：收藏夹
 * =============================================
 */
public class MyCollectedManageActivity extends BaseActivity<MyCollectedManagePresenter> implements IMyCollectedManageView {

    @Inject
    Dialog mDialog;
    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    @Inject
    MyCollectedAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.public_layout_rcy_title;
    }

    @Override
    protected void initView(Bundle bundle) {
        DaggerMyCollectedManageActivityComponent.builder().myCollectedManageActivityModule(new MyCollectedManageActivityModule(this)).build().inject(this);
        publicToolbarTitle.setText(getString(R.string.str_shoucangjia));

        LayoutManagerUtil.initView(mRecyclerView, true, new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                mPresenter.getMyCollectedList(true);
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mPresenter.getMyCollectedList(false);
            }
        }).setAdapter(mAdapter);

    }

    @Override
    protected void initData(Bundle bundle) {
        mAdapter.clear();
        mAdapter.setState(HelperStateRecyclerViewAdapter.STATE_LOADING);
        mPresenter.getMyCollectedList(true);
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
    public Activity getActivity() {
        return this;
    }

    @Override
    public XRecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    @Override
    public HelperStateRecyclerViewAdapter<MyCollectedBean> getAdapter() {
        return mAdapter;
    }

    @Override
    public void onItemShouCangClick(ImageView view, MyCollectedBean item, int position) {
        //将文章移除收藏夹
        mPresenter.postUncollect(item,position);
    }

    @Override
    public void onItemClick(MyCollectedBean item, int position) {
        //查看文章详情
    }


    @OnClick(R.id.public_toolbar_back)
    public void onViewClicked() {
        finish();
    }
}
