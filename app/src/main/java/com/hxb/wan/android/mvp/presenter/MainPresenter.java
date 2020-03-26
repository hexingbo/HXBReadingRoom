package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.view.iview.IMainView;
import com.hxb.wan.android.mvp.model.imodel.IMainModel;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;

public class MainPresenter extends BasePresenter<IMainView, IMainModel> {

    public MainPresenter(IMainView iView, IMainModel iModel) {
        super(iView, iModel);
    }


}
