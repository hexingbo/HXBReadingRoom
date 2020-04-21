package com.hxb.wan.android.mvp.model.entity.event;

import com.hxb.wan.android.mvp.model.entity.em.UserCollectEnum;

/**
 * @Author :hexingbo
 * @Date :2020/4/21
 * @FileName： UserCollectEvent
 * @Describe :取消收藏的消息对象id
 */
public class UserCollectEvent {
    private int id;
    private boolean collect;
    private UserCollectEnum em;

    public UserCollectEvent(int originId, boolean collect, UserCollectEnum em) {
        this.id = originId;
        this.collect = collect;
        this.em=em;
    }

    public int getId() {
        return id;
    }

    public void setId(int originId) {
        this.id = originId;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public UserCollectEnum getEm() {
        return em;
    }

    public void setEm(UserCollectEnum em) {
        this.em = em;
    }
}
