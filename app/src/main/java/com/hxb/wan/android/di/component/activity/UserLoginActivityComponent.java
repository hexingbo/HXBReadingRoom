package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.UserLoginActivityModule;

import com.hxb.wan.android.mvp.view.activity.UserLoginActivity;


@ActivityScope
@Component(modules = UserLoginActivityModule.class)
public interface UserLoginActivityComponent {
    void inject(UserLoginActivity activity);
}