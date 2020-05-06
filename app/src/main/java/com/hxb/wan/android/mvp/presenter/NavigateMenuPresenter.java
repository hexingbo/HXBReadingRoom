package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.INavigateMenuView;
import com.hxb.wan.android.mvp.model.imodel.INavigateMenuModel;

public class NavigateMenuPresenter extends BasePresenter<INavigateMenuView, INavigateMenuModel> {

    public NavigateMenuPresenter(INavigateMenuView iView, INavigateMenuModel iModel) {
        super(iView, iModel);
    }


}
