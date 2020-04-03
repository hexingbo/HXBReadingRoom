package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleListData;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.INewArticleModel;
import com.ljy.devring.DevRing;
import com.ljy.devring.util.AppManagerUtil;

import java.util.List;

import io.reactivex.Observable;

public class NewArticleModel implements INewArticleModel {


    @Override
    public Observable<HttpResult<List<WxArticleDataBean>>> getNewArticleTopList() {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).getWxArticleTopList();
    }

    @Override
    public Observable<HttpResult<WxArticleListData>> getNewArticleList(int page) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).getWxArticleList(page);
    }

    @Override
    public Observable<HttpResult> postCollect(int id) {
        return DevRing.httpManager().getService(HxbWanAndroidService.class).postCollect(id);
    }

    @Override
    public void updateMenuUserCollectNumber(int number) {
       
        AppManagerUtil.getCurrentActivity().finish();
    }


}