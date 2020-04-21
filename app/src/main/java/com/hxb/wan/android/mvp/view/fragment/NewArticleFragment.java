package com.hxb.wan.android.mvp.view.fragment;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.fragment.DaggerNewArticleFragmentComponent;
import com.hxb.wan.android.di.module.fragment.NewArticleFragmentModule;
import com.hxb.wan.android.mvp.model.entity.event.UncollectArticleEvent;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.presenter.NewArticlePresenter;
import com.hxb.wan.android.mvp.view.adapter.NewArticleAdapter;
import com.hxb.wan.android.mvp.view.fragment.base.BaseFragment;
import com.hxb.wan.android.mvp.view.iview.INewArticleView;
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

    private WxArticleDataBean currentBean;
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
                mPresenter.getNewArticleList(false);
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

    @Override
    public void onItemShouCangClick(ImageView view, WxArticleDataBean item, int position) {
        //文章收藏与取消收藏
        currentPosition = position;
        currentBean = item;
        mPresenter.postCollectOrUnCollect(item);
    }

    @Override
    public void onItemClick(WxArticleDataBean item, int position) {
        //查看文章详情
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
        WxArticleDataBean bean = null;
        switch (event.getEm()) {
            case NewArticle:
                p = currentPosition;
                bean = currentBean;
                bean.setCollect(event.isCollect());
                break;
            case NewProject:
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
