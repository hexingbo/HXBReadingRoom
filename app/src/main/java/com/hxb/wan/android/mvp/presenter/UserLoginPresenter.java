package com.hxb.wan.android.mvp.presenter;

import android.text.TextUtils;

import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IUserLoginView;
import com.ljy.devring.DevRing;
import com.ljy.devring.http.support.observer.HttpNetObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.util.DataSPUtils;
import com.ljy.devring.util.RingToast;
import com.ljy.devring.util.RxLifecycleUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.disposables.Disposable;

public class UserLoginPresenter extends BasePresenter<IUserLoginView, IUserLoginModel> {

    public UserLoginPresenter(IUserLoginView iView, IUserLoginModel iModel) {
        super(iView, iModel);
    }


    /**
     * 用户登录
     *
     * @param username
     * @param password
     */
    public void goUserLogin(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            RingToast.show("请输入用户名");
            return;
        }
        DataSPUtils.putString(Constants.SP_LoginUserName, username);
        if (TextUtils.isEmpty(password)) {
            RingToast.show("请输入密码");
            return;
        }

        DevRing.httpManager().commonRequest(mIModel.goUserLogin(username, password), new HttpNetObserver() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                mIView.showLoading();//显示下拉刷新的进度条
            }

            @Override
            public void run() throws Exception {
                mIView.hideLoading();//隐藏下拉刷新的进度条
            }

        }, new MyCommonObserver<HttpResult<UserBean>>() {
            @Override
            public void onResult(HttpResult<UserBean> result) {
                mIModel.updateMenuUserInfo(result.getData());
            }

            @Override
            public void onError(HttpThrowable httpThrowable) {
                RingToast.show(httpThrowable.message);
            }
        }, RxLifecycleUtil.bindUntilEvent(mIView, ActivityEvent.DESTROY));
        
    }

}
