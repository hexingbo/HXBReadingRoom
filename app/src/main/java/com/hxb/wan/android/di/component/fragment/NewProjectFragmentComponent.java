package com.hxb.wan.android.di.component.fragment;

import com.ljy.devring.di.scope.FragmentScope;

import dagger.Component;

import com.hxb.wan.android.di.module.fragment.NewProjectFragmentModule;

import com.hxb.wan.android.mvp.view.fragment.NewProjectFragment;


@FragmentScope
@Component(modules = NewProjectFragmentModule.class)
public interface NewProjectFragmentComponent {
    void inject(NewProjectFragment fragment);
}