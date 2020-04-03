package com.ljy.devring.http.support.observer;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @Author :hexingbo
 * @Date :2020/4/4
 * @FileName： HttpNetObserver
 * @Describe :
 */
public interface HttpNetObserver extends Consumer<Disposable>, Action {

    public void accept(Disposable disposable) throws Exception;

    public void run() throws Exception;
}
