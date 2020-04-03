package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.MyCollectListData;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectListData;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IMyCollectedManageView;
import com.hxb.wan.android.mvp.model.imodel.IMyCollectedManageModel;
import com.ljy.devring.DevRing;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.other.RingLog;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyCollectedManagePresenter extends BasePresenter<IMyCollectedManageView, IMyCollectedManageModel> {

    public MyCollectedManagePresenter(IMyCollectedManageView iView, IMyCollectedManageModel iModel) {
        super(iView, iModel);
    }

    int page;

    public void getMyCollectedList(boolean isRefresh) {
        DevRing.httpManager().commonRequest(mIModel.getMyCollectedList(isRefresh ? page = 0 : page)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        RingLog.d("请求开始");
                    }
                }).doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        RingLog.d("请求结束");
                        if (isRefresh) {
                            mIView.getRecyclerView().refreshComplete();
                        } else {
                            mIView.getRecyclerView().loadMoreComplete();
                        }
                    }
                }), new MyCommonObserver<HttpResult<MyCollectListData>>() {
            @Override
            public void onResult(HttpResult<MyCollectListData> result) {
                if (result.getData() != null) {
                    if (isRefresh) {
                        mIView.getAdapter().setListAll(result.getData().getDatas());
                        mIView.getRecyclerView().setLoadingMoreEnabled(true);
                    } else {
                        mIView.getAdapter().addItemsToLast(result.getData().getDatas());
                    }
                }
            }

            @Override
            public void onError(HttpThrowable throwable) {
                if (isRefresh) {
                    mIView.getAdapter().setState(HelperStateRecyclerViewAdapter.STATE_ERROR);
                    mIView.getAdapter().setState(HelperStateRecyclerViewAdapter.STATE_NORMAL);
                } else {
                    mIView.showMessage(throwable.message);
                }

            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, ActivityEvent.DESTROY));
    }

}
