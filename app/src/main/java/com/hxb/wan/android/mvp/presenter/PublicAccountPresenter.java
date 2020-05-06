package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IPublicAccountView;
import com.hxb.wan.android.mvp.model.imodel.IPublicAccountModel;

public class PublicAccountPresenter extends BasePresenter<IPublicAccountView, IPublicAccountModel> {

    public PublicAccountPresenter(IPublicAccountView iView, IPublicAccountModel iModel) {
        super(iView, iModel);
    }


}
