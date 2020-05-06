package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IProjectHallView;
import com.hxb.wan.android.mvp.model.imodel.IProjectHallModel;

public class ProjectHallPresenter extends BasePresenter<IProjectHallView, IProjectHallModel> {

    public ProjectHallPresenter(IProjectHallView iView, IProjectHallModel iModel) {
        super(iView, iModel);
    }


}
