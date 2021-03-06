package com.hxb.wan.android.mvp.view.iview;

import android.app.Activity;
import android.content.Context;

import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.view.iview.base.IBaseView;

import java.util.List;


public interface IMainView extends IBaseView {

    void getBannerListSuccess(List<BannerData> data);

    Activity getActivity();
}
