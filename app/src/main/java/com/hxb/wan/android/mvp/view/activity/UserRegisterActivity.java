package com.hxb.wan.android.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.activity.DaggerUserRegisterActivityComponent;
import com.hxb.wan.android.di.module.activity.UserRegisterActivityModule;
import com.hxb.wan.android.mvp.presenter.UserRegisterPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.iview.IUserRegisterView;
import com.hxb.wan.android.mvp.view.weight.MyEditDeleteTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/3/26
 * 描    述：用户注册
 * =============================================
 */
public class UserRegisterActivity extends BaseActivity<UserRegisterPresenter> implements IUserRegisterView {

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
                mPresenter.jumpUserLoginActivity();
                break;
            case R.id.btn_register:
                mPresenter.goUserRegister(etUsername.getText().toString().trim(),
                        etPassword.getText().toString().trim(),
                        etRepassword.getText().toString().trim());
                break;

        }
    }


}
