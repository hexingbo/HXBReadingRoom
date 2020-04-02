package com.hxb.wan.android.mvp.view.iview;

import android.app.Activity;

import com.hxb.wan.android.mvp.view.adapter.NewProjectAdapter;
import com.hxb.wan.android.mvp.view.iview.base.IBaseView;
import com.zhouyou.recyclerview.XRecyclerView;


public interface INewProjectView extends IBaseView {

    Activity getActivity();

    XRecyclerView getRecyclerView();
    
    NewProjectAdapter getAdapter();
}
