package com.hxb.reading.room.di.component.activity;

import com.hxb.reading.room.di.module.activity.StartActivityModule;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.reading.room.mvp.view.activity.StartActivity;


@ActivityScope
@Component(modules = StartActivityModule.class)
public interface StartActivityComponent {
    void inject(StartActivity activity);
}