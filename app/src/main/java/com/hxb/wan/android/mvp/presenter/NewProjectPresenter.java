package com.hxb.wan.android.mvp.presenter;

import android.widget.ImageView;

import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectListData;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.INewProjectView;
import com.hxb.wan.android.mvp.model.imodel.INewProjectModel;
import com.ljy.devring.DevRing;
import com.ljy.devring.http.support.observer.HttpNetObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.other.RingLog;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NewProjectPresenter extends BasePresenter<INewProjectView, INewProjectModel> {

    public NewProjectPresenter(INewProjectView iView, INewProjectModel iModel) {
        super(iView, iModel);
    }

    private int page;

    public void getNewProjectList(boolean isRefresh) {
        DevRing.httpManager().commonRequest(mIModel.getNewProjectList(isRefresh ? page = 0 : page),
                new HttpNetObserver() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        RingLog.d("请求开始");
//                        mIView.showLoading();
                    }

                    @Override
                    public void run() throws Exception {
                        RingLog.d("请求结束");
//                        mIView.hideLoading();
                        if (isRefresh) {
                            mIView.getRecyclerView().refreshComplete();
                        } else {
//                            mIView.getRecyclerView().loadMoreComplete();
                        }
                    }
                }, new MyCommonObserver<HttpResult<WxProjectListData>>() {
                    @Override
                    public void onResult(HttpResult<WxProjectListData> result) {
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
                }, RxLifecycleUtil.bindUntilEvent(mIView, FragmentEvent.DESTROY));
    }

    /**
     * 添加到我的收藏夹或者移除我的收藏夹
     *
     * @param view 收藏icon
     * @param item 当前操作的实体对象
     */
    public void postCollectOrUnCollect(ImageView view, WxProjectDataBean item) {
        boolean add = !item.isCollect();
        DevRing.httpManager().commonRequest(mIModel.postCollectOrUnCollect(add, item.getId()), new HttpNetObserver() {
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
                item.setCollect(add);
                view.setSelected(add);
                mIModel.updateMenuUserCollectNumber(MainDataEvent.init().getUserCollectedNumber() + (add ? 1 : -1));
            }

            @Override
            public void onError(HttpThrowable throwable) {
                mIView.showMessage(throwable.message);

            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, FragmentEvent.DESTROY));
    }

}
