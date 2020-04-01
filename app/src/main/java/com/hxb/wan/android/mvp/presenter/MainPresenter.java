package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.view.iview.IMainView;
import com.hxb.wan.android.mvp.model.imodel.IMainModel;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.ljy.devring.DevRing;
import com.ljy.devring.http.support.observer.CommonObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.other.RingLog;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.List;

public class MainPresenter extends BasePresenter<IMainView, IMainModel> {

    public MainPresenter(IMainView iView, IMainModel iModel) {
        super(iView, iModel);
    }

    
    public void getBannerList(){
        DevRing.httpManager().commonRequest(mIModel.getBannerList(), new MyCommonObserver<HttpResult<List<BannerData>>>() {
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

}
