package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IAboutUsView;
import com.hxb.wan.android.mvp.model.imodel.IAboutUsModel;

public class AboutUsPresenter extends BasePresenter<IAboutUsView, IAboutUsModel> {

    public AboutUsPresenter(IAboutUsView iView, IAboutUsModel iModel) {
        super(iView, iModel);
    }


}
