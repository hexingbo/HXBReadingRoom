package com.hxb.wan.android.di.component.activity;

import com.ljy.devring.di.scope.ActivityScope;

import dagger.Component;

import com.hxb.wan.android.di.module.activity.KnowledgeSystemActivityModule;

import com.hxb.wan.android.mvp.view.activity.KnowledgeSystemActivity;


@ActivityScope
@Component(modules = KnowledgeSystemActivityModule.class)
public interface KnowledgeSystemActivityComponent {
    void inject(KnowledgeSystemActivity activity);
}