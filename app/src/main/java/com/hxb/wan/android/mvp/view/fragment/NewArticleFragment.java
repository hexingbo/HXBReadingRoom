package com.hxb.wan.android.mvp.view.fragment;

import android.support.annotation.NonNull;

import com.hxb.wan.android.R;
import com.hxb.wan.android.mvp.presenter.NewArticlePresenter;
import com.hxb.wan.android.mvp.view.fragment.base.BaseFragment;
import com.hxb.wan.android.mvp.view.iview.INewArticleView;

/**
 * =============================================
 * 作    者：贺兴波
 * 时    间：2020/4/1
 * 描    述： NewArticleFragment 最新文章
 * =============================================
 */
public class NewArticleFragment extends BaseFragment<NewArticlePresenter> implements INewArticleView {

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_new_article;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

}
