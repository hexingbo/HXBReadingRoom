package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.INewProjectView;
import com.hxb.wan.android.mvp.model.imodel.INewProjectModel;

public class NewProjectPresenter extends BasePresenter<INewProjectView, INewProjectModel> {

    public NewProjectPresenter(INewProjectView iView, INewProjectModel iModel) {
        super(iView, iModel);
    }


}
