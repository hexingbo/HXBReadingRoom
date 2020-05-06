package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.ProjectHallActivityModule;

import com.hxb.wan.android.mvp.view.activity.ProjectHallActivity;


@ActivityScope
@Component(modules = ProjectHallActivityModule.class)
public interface ProjectHallActivityComponent {
    void inject(ProjectHallActivity activity);
}