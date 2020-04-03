package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hxb.wan.android.R;
import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.di.component.activity.DaggerUserLoginActivityComponent;
import com.hxb.wan.android.di.module.activity.UserLoginActivityModule;
import com.hxb.wan.android.mvp.model.entity.res.UserBean;
import com.hxb.wan.android.mvp.presenter.UserLoginPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.iview.IUserLoginView;
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
 * 描    述：用户登录
 * =============================================
 */
public class UserLoginActivity extends BaseActivity<UserLoginPresenter> implements IUserLoginView {

    @Inject
    Dialog mDialog;

    @BindView(R.id.public_toolbar_back)
    RelativeLayout publicToolbarBack;
    @BindView(R.id.et_username)
    MyEditDeleteTextView etUserName;
    @BindView(R.id.et_password)
    MyEditDeleteTextView etPassword;
    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initView(Bundle bundle) {
        DaggerUserLoginActivityComponent.builder().userLoginActivityModule(new UserLoginActivityModule(this)).build().inject(this);
        publicToolbarBack.setVisibility(View.INVISIBLE);
        publicToolbarTitle.setText("登录");
    }

    @Override
    protected void initData(Bundle bundle) {
        etUserName.setText(DataSPUtils.getString(Constants.SP_LoginUserName, ""));
    }

    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.btn_login)
    public void onViewClicked_Login() {
        mPresenter.goUserLogin(etUserName.getText().toString().trim(), etPassword.getText().toString().trim());
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked_Register() {
        AppManagerUtil.jumpAndFinish(UserRegisterActivity.class);
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
    public Activity getActivity() {
        return this;
    }

}
