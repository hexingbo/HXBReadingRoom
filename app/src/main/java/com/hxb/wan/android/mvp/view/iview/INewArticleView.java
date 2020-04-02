package com.hxb.wan.android.mvp.view.iview;

import android.app.Activity;

import com.hxb.wan.android.mvp.view.adapter.NewArticleAdapter;
import com.hxb.wan.android.mvp.view.iview.base.IBaseView;
import com.zhouyou.recyclerview.XRecyclerView;


public interface INewArticleView extends IBaseView {

    Activity getActivity();

    XRecyclerView getRecyclerView();

    NewArticleAdapter getAdapter();
}
