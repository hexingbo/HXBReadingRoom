package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.UserRegisterActivityModule;

import com.hxb.wan.android.mvp.view.activity.UserRegisterActivity;


@ActivityScope
@Component(modules = UserRegisterActivityModule.class)
public interface UserRegisterActivityComponent {
    void inject(UserRegisterActivity activity);
}