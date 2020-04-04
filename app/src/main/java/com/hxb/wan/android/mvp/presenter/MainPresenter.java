package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.view.iview.IMainView;
import com.hxb.wan.android.mvp.model.imodel.IMainModel;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.ljy.devring.DevRing;
import com.ljy.devring.http.support.observer.CommonObserver;
import com.ljy.devring.http.support.observer.HttpNetObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.other.RingLog;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MainPresenter extends BasePresenter<IMainView, IMainModel> {

    public MainPresenter(IMainView iView, IMainModel iModel) {
        super(iView, iModel);
    }


    /**
     * 获取广告数据
     */
    public void getBannerList() {
        DevRing.httpManager().commonRequest(mIModel.getBannerList(), new HttpNetObserver() {
            @Override
            public void accept(Disposable disposable) throws Exception {
//                mIView.showLoading();
            }

            @Override
            public void run() throws Exception {
//                mIView.hideLoading();
            }
        }, new MyCommonObserver<HttpResult<List<BannerData>>>() {
            @Override
            public void onResult(HttpResult<List<BannerData>> result) {
                if (mIView != null) {
                    mIView.getBannerListSuccess(result.getData());
                }
            }

            @Override
            public void onError(HttpThrowable throwable) {
            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, ActivityEvent.DESTROY));
    }

    /**
     * 退出登录
     */
    public void goLogOutUser() {
        DevRing.httpManager().commonRequest(mIModel.goLogOutUser(), new HttpNetObserver() {
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
                mIModel.clearUserInfo();
            }

            @Override
            public void onError(HttpThrowable throwable) {
                mIView.showMessage(throwable.message);
            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, ActivityEvent.DESTROY));
    }

}
