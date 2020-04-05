package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectListData;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectedBean;
import com.hxb.wan.android.mvp.model.imodel.IMyCollectedManageModel;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IMyCollectedManageView;
import com.ljy.devring.DevRing;
import com.ljy.devring.http.support.observer.HttpNetObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.other.RingLog;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import io.reactivex.disposables.Disposable;

public class MyCollectedManagePresenter extends BasePresenter<IMyCollectedManageView, IMyCollectedManageModel> {

    public MyCollectedManagePresenter(IMyCollectedManageView iView, IMyCollectedManageModel iModel) {
        super(iView, iModel);
    }

    int page;

    public void getMyCollectedList(boolean isRefresh) {
        DevRing.httpManager().commonRequest(mIModel.getMyCollectedList(isRefresh ? page = 0 : page), new HttpNetObserver() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
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
                }
                , new MyCommonObserver<HttpResult<MyCollectListData>>() {

                    @Override
                    public void onResult(HttpResult<MyCollectListData> result) {
                        mIView.getRecyclerView().setLoadingMoreEnabled(true);
                        if (result.getData() != null && result.getData().getDatas() != null && result.getData().getDatas().size() != 0) {
                            page += 1;
                            if (isRefresh) {
                                mIView.getAdapter().setListAll(result.getData().getDatas());
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
                            mIView.getAdapter().setState(HelperStateRecyclerViewAdapter.STATE_ERROR);
                        } else {
                            mIView.getRecyclerView().loadMoreComplete();
                            mIView.showMessage(throwable.message);
                        }
                    }
                }, RxLifecycleUtil.bindUntilEvent(mIView, ActivityEvent.DESTROY));
    }

    /**
     * 将文章移除收藏夹
     *
     * @param bean
     * @param position
     */
    public void postUncollect(MyCollectedBean bean, int position) {
        DevRing.httpManager().commonRequest(mIModel.postUnCollect(bean.getId(), bean.getOriginId() <= 0 ? -1 : bean.getOriginId()), new HttpNetObserver() {
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
                mIView.getAdapter().removeToIndex(position);
                mIModel.updateMenuUserCollectNumber(MainDataEvent.init().getUserCollectedNumber() - 1);
            }

            @Override
            public void onError(HttpThrowable throwable) {
                mIView.showMessage(throwable.message);

            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, ActivityEvent.DESTROY));
    }

}
