package com.hxb.wan.android.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.app.constant.Constants;
import com.hxb.wan.android.di.component.activity.DaggerUserLoginActivityComponent;
import com.hxb.wan.android.di.module.activity.UserLoginActivityModule;
import com.hxb.wan.android.mvp.presenter.UserLoginPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.iview.IUserLoginView;
import com.hxb.wan.android.mvp.view.weight.MyEditDeleteTextView;
import com.ljy.devring.util.DataSPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/3/26
 * 描    述：用户登录
 * =============================================
 */
public class UserLoginActivity extends BaseActivity<UserLoginPresenter> implements IUserLoginView {

    @BindView(R.id.public_toolbar_back)
    RelativeLayout publicToolbarBack;
    @BindView(R.id.et_user)
    MyEditDeleteTextView etUser;
    @BindView(R.id.et_pwd)
    MyEditDeleteTextView etPwd;
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
        etUser.setText(DataSPUtils.getString(Constants.SP_LoginUserName, ""));
    }

    @Override
    protected void initEvent() {

    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mPresenter.goUserLogin(etUser.getText().toString().trim(), etPwd.getText().toString().trim());
    }

}
