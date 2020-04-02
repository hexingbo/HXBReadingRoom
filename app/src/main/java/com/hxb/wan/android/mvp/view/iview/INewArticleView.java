package com.hxb.wan.android.mvp.view.iview;

import android.app.Activity;

import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.view.adapter.NewArticleAdapter;
import com.hxb.wan.android.mvp.view.iview.base.IBaseView;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.DataHelper;

import java.util.List;


public interface INewArticleView extends IBaseView {

    Activity getActivity();

    List<WxArticleDataBean> getDataList();

    XRecyclerView getRecyclerView();

    NewArticleAdapter getAdapter();
}
