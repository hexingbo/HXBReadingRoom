package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleListData;
import com.hxb.wan.android.mvp.model.imodel.INewArticleModel;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.INewArticleView;
import com.ljy.devring.DevRing;
import com.ljy.devring.http.support.observer.HttpNetObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.other.RingLog;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class NewArticlePresenter extends BasePresenter<INewArticleView, INewArticleModel> {

    public NewArticlePresenter(INewArticleView iView, INewArticleModel iModel) {
        super(iView, iModel);
    }

    private int page;
    private List<WxArticleDataBean> listTop = new ArrayList<>();

    /**
     * 获取置顶文章
     */
    public void getNewArticleTopList() {
        page = 0;
        DevRing.httpManager().commonRequest(mIModel.getNewArticleTopList(), new HttpNetObserver() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }

                    @Override
                    public void run() throws Exception {
                        getNewArticleList(true);
                    }
                },
                new MyCommonObserver<HttpResult<List<WxArticleDataBean>>>() {
                    @Override
                    public void onResult(HttpResult<List<WxArticleDataBean>> result) {
                        listTop.clear();
                        listTop.addAll(result.getData());
                    }

                    @Override
                    public void onError(HttpThrowable throwable) {
                    }
                }, RxLifecycleUtil.bindUntilEvent(mIView, FragmentEvent.DESTROY));
    }

    /**
     * 获取文章列表
     */
    public void getNewArticleList(boolean isRefresh) {
        DevRing.httpManager().commonRequest(mIModel.getNewArticleList(page), new HttpNetObserver() {
            @Override
            public void accept(Disposable disposable) throws Exception {
//                mIView.showLoading();
                RingLog.d("请求开始");
            }

            @Override
            public void run() throws Exception {
                if (isRefresh) {
                    mIView.getRecyclerView().refreshComplete();
                } else {
//                            mIView.getRecyclerView().loadMoreComplete();
                }
            }
        }, new MyCommonObserver<HttpResult<WxArticleListData>>() {
            @Override
            public void onResult(HttpResult<WxArticleListData> result) {
                mIView.getRecyclerView().setLoadingMoreEnabled(true);
                if (result.getData() != null && result.getData().getDatas() != null && result.getData().getDatas().size() != 0) {
                    page += 1;
                    if (isRefresh) {
                        mIView.getAdapter().setListAll(result.getData().getDatas());
                        mIView.getAdapter().addItemsToHead(listTop);
                    } else {
                        mIView.getAdapter().addItemsToLast(result.getData().getDatas());
                    }
                    mIView.getRecyclerView().setNoMore(false);
                } else {
                    if (isRefresh) {
                        mIView.getAdapter().setState(HelperStateRecyclerViewAdapter.STATE_EMPTY);
                    } else {
                        mIView.getRecyclerView().setNoMore(true);
                    }
                }
            }

            @Override
            public void onError(HttpThrowable throwable) {
                if (isRefresh) {
                    mIView.getAdapter().addItemsToHead(listTop);
                    if (mIView.getAdapter().getList().size() == 0)
                        mIView.getAdapter().setState(HelperStateRecyclerViewAdapter.STATE_ERROR);
                } else {
                    mIView.getRecyclerView().setNoMore(true);
                    mIView.getRecyclerView().loadMoreComplete();
                    mIView.showMessage(throwable.message);
                }
            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, FragmentEvent.DESTROY));
    }

    /**
     * 添加到我的收藏夹或者移除我的收藏夹
     *
     * @param item     当前操作的实体对象
     */
    public void postCollectOrUnCollect( WxArticleDataBean item) {
        boolean collect = !item.isCollect();
        DevRing.httpManager().commonRequest(mIModel.postCollectOrUnCollect(collect, item.getId()), new HttpNetObserver() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mIView.showLoading();
            }

            @Override
            public void run() throws Exception {
                mIView.hideLoading();
            }
        }, new MyCommonObserver<HttpResult>() {
            @Override
            public void onResult(HttpResult result) {
                mIModel.updateMenuUserCollectNumber(MainDataEvent.init().getUserCollectedNumber() + (collect ? 1 : -1),item.getId(), collect);
            }

            @Override
            public void onError(HttpThrowable throwable) {
                mIView.showMessage(throwable.message);

            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, FragmentEvent.DESTROY));
    }


}
