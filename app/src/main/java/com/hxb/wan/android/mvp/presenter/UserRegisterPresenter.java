package com.hxb.wan.android.mvp.presenter;

import android.text.TextUtils;

import com.hxb.wan.android.mvp.model.Observer.MyCommonObserver;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IUserRegisterView;
import com.hxb.wan.android.mvp.model.imodel.IUserRegisterModel;
import com.ljy.devring.http.support.throwable.HttpThrowable;
import com.ljy.devring.util.RingToast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRegisterPresenter extends BasePresenter<IUserRegisterView, IUserRegisterModel> {

    public UserRegisterPresenter(IUserRegisterView iView, IUserRegisterModel iModel) {
        super(iView, iModel);
    }


    public void jumpUserLoginActivity() {
        mIModel.jumpUserLoginActivity();
    }

    public void goUserRegister(String username, String password, final String repassword) {
        if (TextUtils.isEmpty(username)) {
            RingToast.show("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            RingToast.show("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(repassword)) {
            RingToast.show("请确认密码");
            return;
        }

        mIModel.goUserRegister(username, password, repassword)
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
}
