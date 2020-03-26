package com.hxb.wan.android.di.component.activity;

import com.hxb.wan.android.di.module.activity.MainActivityModule;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.mvp.view.activity.MainActivity;


@ActivityScope
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}