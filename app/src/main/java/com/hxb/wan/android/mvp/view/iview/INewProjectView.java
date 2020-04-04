package com.hxb.wan.android.mvp.view.iview;

import android.app.Activity;
import android.widget.ImageView;

import com.hxb.wan.android.mvp.model.entity.res.WxProjectDataBean;
import com.hxb.wan.android.mvp.view.adapter.NewProjectAdapter;
import com.hxb.wan.android.mvp.view.iview.base.IBaseView;
import com.zhouyou.recyclerview.XRecyclerView;


public interface INewProjectView extends IBaseView {

    Activity getActivity();

    XRecyclerView getRecyclerView();
    
    NewProjectAdapter getAdapter();

    void onItemShoucangClick(ImageView view, WxProjectDataBean item, int position);

    void onItemClick(WxProjectDataBean item, int position);
}
