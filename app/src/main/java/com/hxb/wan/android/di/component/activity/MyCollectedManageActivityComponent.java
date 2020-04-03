package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.MyCollectedManageActivityModule;

import com.hxb.wan.android.mvp.view.activity.MyCollectedManageActivity;


@ActivityScope
@Component(modules = MyCollectedManageActivityModule.class)
public interface MyCollectedManageActivityComponent {
    void inject(MyCollectedManageActivity activity);
}