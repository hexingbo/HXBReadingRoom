package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IWebViewView;
import com.hxb.wan.android.mvp.model.imodel.IWebViewModel;

public class WebViewPresenter extends BasePresenter<IWebViewView, IWebViewModel> {

    public WebViewPresenter(IWebViewView iView, IWebViewModel iModel) {
        super(iView, iModel);
    }


}
