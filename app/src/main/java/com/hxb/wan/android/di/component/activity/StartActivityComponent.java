package com.hxb.wan.android.di.component.activity;

import com.hxb.wan.android.di.module.activity.StartActivityModule;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.mvp.view.activity.StartActivity;


@ActivityScope
@Component(modules = StartActivityModule.class)
public interface StartActivityComponent {
    void inject(StartActivity activity);
}