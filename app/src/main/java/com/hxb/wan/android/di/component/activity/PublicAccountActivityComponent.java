package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.PublicAccountActivityModule;

import com.hxb.wan.android.mvp.view.activity.PublicAccountActivity;


@ActivityScope
@Component(modules = PublicAccountActivityModule.class)
public interface PublicAccountActivityComponent {
    void inject(PublicAccountActivity activity);
}