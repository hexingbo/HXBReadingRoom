package com.hxb.wan.android.di.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import dagger.Component;

import com.hxb.wan.android.di.module.fragment.NewArticleFragmentModule;

import com.hxb.wan.android.mvp.view.fragment.NewArticleFragment;


@FragmentScope
@Component(modules = NewArticleFragmentModule.class)
public interface NewArticleFragmentComponent {
    void inject(NewArticleFragment fragment);
}