package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.NavigateMenuActivityModule;

import com.hxb.wan.android.mvp.view.activity.NavigateMenuActivity;


@ActivityScope
@Component(modules = NavigateMenuActivityModule.class)
public interface NavigateMenuActivityComponent {
    void inject(NavigateMenuActivity activity);
}