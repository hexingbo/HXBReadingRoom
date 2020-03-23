package com.hxb.reading.room.di.component.activity;

import com.hxb.reading.room.di.module.activity.MainActivityModule;
import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.reading.room.mvp.view.activity.MainActivity;


@ActivityScope
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}