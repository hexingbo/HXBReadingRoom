package com.hxb.wan.android.mvp.view.iview.base;

import android.support.annotation.NonNull;

/**
 * author:  ljy
 * date:    2017/9/15
 * description: 可在此加入View层经常需要实现的方法
 * <p>
 * <a>https://www.jianshu.com/p/1f91cfd68d48</a>
 */

public interface IBaseView {

    void showLoading();

    void hideLoading();

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 {@code null}
     */
    void showMessage(@NonNull String message);
    

}
