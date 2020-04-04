package com.hxb.wan.android.mvp.presenter;

import android.widget.ImageView;

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

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class NewArticlePresenter extends BasePresenter<INewArticleView, INewArticleModel> {

    public NewArticlePresenter(INewArticleView iView, INewArticleModel iModel) {
        super(iView, iModel);
    }

    private int page;

    /**
     * 获取置顶文章
     */
    public void getNewArticleTopList() {
        page = 0;
        DevRing.httpManager().commonRequest(mIModel.getNewArticleTopList(), new HttpNetObserver() {
            @Override
            public void accept(Disposable disposable) throws Exception {
//                mIView.showLoading();
                RingLog.d("请求开始");
            }

            @Override
            public void run() throws Exception {
//                mIView.hideLoading();
                RingLog.d("请求结束");
                mIView.getRecyclerView().refreshComplete();
            }
        }, new MyCommonObserver<HttpResult<List<WxArticleDataBean>>>() {
            @Override
            public void onResult(HttpResult<List<WxArticleDataBean>> result) {
                mIView.getAdapter().setListAll(result.getData());
                mIView.getRecyclerView().setLoadingMoreEnabled(true);
            }

            @Override
            public void onError(HttpThrowable throwable) {
                getNewArticleList();
            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, FragmentEvent.DESTROY));
    }

    /**
     * 获取文章列表
     */
    public void getNewArticleList() {
        DevRing.httpManager().commonRequest(mIModel.getNewArticleList(page), new HttpNetObserver() {
            @Override
            public void accept(Disposable disposable) throws Exception {
//                mIView.showLoading();
                RingLog.d("请求开始");
            }

            @Override
            public void run() throws Exception {
//                mIView.hideLoading();
                RingLog.d("请求结束");
                mIView.getRecyclerView().loadMoreComplete();
            }
        }, new MyCommonObserver<HttpResult<WxArticleListData>>() {
            @Override
            public void onResult(HttpResult<WxArticleListData> result) {
                RingLog.d("返回数据-->" + result.toString());
                if (result.getData() != null) {
                    page += 1;
                    mIView.getAdapter().addItemsToLast(result.getData().getDatas());
                } else {
                    if (mIView.getAdapter().getList().size() == 0)
                        mIView.getAdapter().setState(HelperStateRecyclerViewAdapter.STATE_EMPTY);
                }
                mIView.getRecyclerView().setLoadingMoreEnabled(true);
            }

            @Override
            public void onError(HttpThrowable throwable) {
                if (mIView.getAdapter().getList().size() == 0)
                    mIView.getAdapter().setState(HelperStateRecyclerViewAdapter.STATE_ERROR);

            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, FragmentEvent.DESTROY));
    }

    /**
     * 添加到我的收藏夹或者移除我的收藏夹
     *
     * @param view 收藏icon
     * @param item 当前操作的实体对象
     */
    public void postCollectOrUnCollect(ImageView view, WxArticleDataBean item) {
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
