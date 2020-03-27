package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hxb.wan.android.R;
import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.di.component.activity.DaggerUserRegisterActivityComponent;
import com.hxb.wan.android.di.module.activity.UserRegisterActivityModule;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.presenter.UserRegisterPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.iview.IUserRegisterView;
import com.hxb.wan.android.mvp.view.weight.MyEditDeleteTextView;
import com.ljy.devring.util.AppManagerUtil;
import com.ljy.devring.util.DataSPUtils;
import com.ljy.devring.util.RingToast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/3/26
 * 描    述：用户注册
 * =============================================
 */
public class UserRegisterActivity extends BaseActivity<UserRegisterPresenter> implements IUserRegisterView {

    @Inject
    Dialog mDialog;

    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;
    @BindView(R.id.et_username)
    MyEditDeleteTextView etUsername;
    @BindView(R.id.et_password)
    MyEditDeleteTextView etPassword;
    @BindView(R.id.et_repassword)
    MyEditDeleteTextView etRepassword;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_user_register;
    }

    @Override
    protected void initView(Bundle bundle) {
        DaggerUserRegisterActivityComponent.builder().userRegisterActivityModule(new UserRegisterActivityModule(this)).build().inject(this);
        publicToolbarTitle.setText("注册");
    }

    @Override
    protected void initData(Bundle bundle) {

    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.public_toolbar_back, R.id.btn_register, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.public_toolbar_back:
            case R.id.btn_login:
                jumpUserLoginActivity();
                break;
            case R.id.btn_register:
                mPresenter.goUserRegister(etUsername.getText().toString().trim(),
                        etPassword.getText().toString().trim(),
                        etRepassword.getText().toString().trim());
                break;

        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        RingToast.show(message);
    }

    @Override
    public void jumpMainActivity(UserBean userBean) {
        DataSPUtils.putString(Constants.SP_UserBean, new Gson().toJson(userBean));
        AppManagerUtil.jumpAndFinish(MainActivity.class);
    }

    @Override
    public void jumpUserLoginActivity() {
        AppManagerUtil.jumpAndFinish(UserLoginActivity.class);
    }

}
