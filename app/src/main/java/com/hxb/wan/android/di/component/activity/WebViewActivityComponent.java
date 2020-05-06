package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.WebViewActivityModule;

import com.hxb.wan.android.mvp.view.activity.WebViewActivity;


@ActivityScope
@Component(modules = WebViewActivityModule.class)
public interface WebViewActivityComponent {
    void inject(WebViewActivity activity);
}