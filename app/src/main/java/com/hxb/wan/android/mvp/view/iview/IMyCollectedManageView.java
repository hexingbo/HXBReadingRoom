package com.hxb.wan.android.mvp.view.iview;

import android.app.Activity;

import com.hxb.wan.android.mvp.model.entity.res.MyCollectedBean;
import com.hxb.wan.android.mvp.view.iview.base.IBaseView;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;


public interface IMyCollectedManageView extends IBaseView {

    Activity getActivity();

    XRecyclerView getRecyclerView();

    HelperStateRecyclerViewAdapter<MyCollectedBean> getAdapter();
}
