package com.hxb.wan.android.mvp.model.entity.event;

/**
 * @Author :hexingbo
 * @Date :2020/4/3
 * @FileName： MainDataEvent
 * @Describe :
 */
public class MainDataEvent {
    
    private boolean isLogin;

    public MainDataEvent(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
