package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.AboutUsActivityModule;

import com.hxb.wan.android.mvp.view.activity.AboutUsActivity;


@ActivityScope
@Component(modules = AboutUsActivityModule.class)
public interface AboutUsActivityComponent {
    void inject(AboutUsActivity activity);
}