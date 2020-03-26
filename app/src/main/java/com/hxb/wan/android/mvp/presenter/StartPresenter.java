package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IStartView;
import com.hxb.wan.android.mvp.model.imodel.IStartModel;

public class StartPresenter extends BasePresenter<IStartView, IStartModel> {

    public StartPresenter(IStartView iView, IStartModel iModel) {
        super(iView, iModel);
    }


}
