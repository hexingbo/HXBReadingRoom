package com.hxb.wan.android.mvp.model.imodel;

import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.imodel.base.IBaseModel;

import java.util.List;

import io.reactivex.Observable;


public interface IMainModel extends IBaseModel {

    //获取广告数据
    Observable<HttpResult<List<BannerData>>> getBannerList();

}
