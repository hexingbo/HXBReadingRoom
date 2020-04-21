package com.hxb.wan.android.mvp.model;

import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.event.UserCollectEvent;
import com.hxb.wan.android.mvp.model.entity.em.UserCollectEnum;
import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleListData;
import com.hxb.wan.android.mvp.model.http.HxbWanAndroidService;
import com.hxb.wan.android.mvp.model.imodel.INewArticleModel;
import com.ljy.devring.DevRing;

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
    public Observable<HttpResult> postCollectOrUnCollect(boolean add, int id) {
        if (add) {
            return DevRing.httpManager().getService(HxbWanAndroidService.class).postCollect(id);
        } else {
            return DevRing.httpManager().getService(HxbWanAndroidService.class).postUncollectOriginId(id);
        }
    }

    @Override
    public void updateMenuUserCollectNumber(int number, int id, boolean collect) {
        DevRing.busManager().postEvent(MainDataEvent.init().setUserCollectedNumber(number));
        DevRing.busManager().postEvent(new UserCollectEvent(id, collect, UserCollectEnum.NewArticle));
    }


}