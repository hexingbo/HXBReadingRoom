package com.hxb.wan.android.mvp.view.fragment;


import android.app.Dialog;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.fragment.DaggerNewProjectFragmentComponent;
import com.hxb.wan.android.di.module.fragment.NewProjectFragmentModule;
import com.hxb.wan.android.mvp.model.entity.event.UncollectArticleEvent;
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

    private WxProjectDataBean currentBean;
    private int currentPosition;

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

    @Override
    public void onItemShoucangClick(ImageView view, WxProjectDataBean item, int position) {
        //文章的收藏与取消收藏
        currentPosition = position;
        currentBean = item;
        mPresenter.postCollectOrUnCollect(item);
    }

    @Override
    public void onItemClick(WxProjectDataBean item, int position) {
        //查看最新项目的详情
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    //接收事件总线发来的事件
//    @org.greenrobot.eventbus.Subscribe //如果使用默认的EventBus则使用此@Subscribe
    @com.hxb.wan.android.mvp.model.bus.support.Subscribe //如果使用RxBus则使用此@Subscribe
    public void handleEvent(UncollectArticleEvent event) {
        int p = -1;
        WxProjectDataBean bean = null;
        switch (event.getEm()) {
            case NewProject:
                p = currentPosition;
                bean = currentBean;
                bean.setCollect(event.isCollect());
                break;
            case NewArticle:
            case CollectedManage:
                for (int i = 0; i < mAdapter.getList().size(); i++) {
                    bean = mAdapter.getList().get(i);
                    if (bean.getId() == event.getId()) {
                        p = i;
                        bean.setCollect(event.isCollect());
                        break;
                    }
                }
                break;
        }
        if (p != -1) {
            mAdapter.alterObj(p, bean);
        }
    }
}
