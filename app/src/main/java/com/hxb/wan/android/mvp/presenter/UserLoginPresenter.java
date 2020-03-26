package com.hxb.wan.android.mvp.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.activity.MainActivity;
import com.hxb.wan.android.mvp.view.iview.IUserLoginView;
import com.hxb.wan.android.mvp.model.imodel.IUserLoginModel;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.util.AppManagerUtil;
import com.ljy.devring.util.DataSPUtils;
import com.ljy.devring.util.RingToast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        mIModel.goUserLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyCommonObserver<HttpResult<UserBean>>() {
                    @Override
                    public void onResult(HttpResult<UserBean> result) {
                        mIModel.jumpMainActivity(result.getData());
                    }

                    @Override
                    public void onError(HttpThrowable httpThrowable) {
                        RingToast.show(httpThrowable.message);
                    }
                });
    }

    /**
     * 注册
     */
    public void jumpUserRegisterActivity() {
        mIModel.jumpUserRegisterActivity();
    }
}
