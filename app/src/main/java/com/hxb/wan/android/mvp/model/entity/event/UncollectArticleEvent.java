package com.hxb.wan.android.mvp.model.entity.event;

/**
 * @Author :hexingbo
 * @Date :2020/4/21
 * @FileName： UncollectArticleEvent
 * @Describe :取消收藏的消息对象id
 */
public class UncollectArticleEvent {
    private int id;
    private boolean collect;
    private UserCollectEm em;

    public UncollectArticleEvent(int originId, boolean collect,UserCollectEm em) {
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

    public UserCollectEm getEm() {
        return em;
    }

    public void setEm(UserCollectEm em) {
        this.em = em;
    }
}
