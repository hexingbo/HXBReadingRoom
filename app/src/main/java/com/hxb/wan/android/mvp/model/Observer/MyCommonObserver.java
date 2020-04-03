package com.hxb.wan.android.mvp.model.Observer;


import com.hxb.wan.android.mvp.model.entity.res.HttpResult;
import com.ljy.devring.http.support.observer.CommonObserver;
import com.ljy.devring.http.support.throwable.HttpThrowable;

/**
 * author:  ljy
 * date:    2017/9/27
 * description:  普通的api请求回调
 */

public abstract class MyCommonObserver<T extends HttpResult> extends CommonObserver<T> {

    @Override
    public void onNext(T httpResult) {
        if (httpResult.getErrorCode() == 0) {
            onResult(httpResult);
        } else {
            onError(new HttpThrowable(HttpThrowable.UNKNOWN, httpResult.getErrorMsg(), null));
        }

    }
    
}
