package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IUserRegisterView;
import com.hxb.wan.android.mvp.model.imodel.IUserRegisterModel;

public class UserRegisterPresenter extends BasePresenter<IUserRegisterView, IUserRegisterModel> {

    public UserRegisterPresenter(IUserRegisterView iView, IUserRegisterModel iModel) {
        super(iView, iModel);
    }


}
