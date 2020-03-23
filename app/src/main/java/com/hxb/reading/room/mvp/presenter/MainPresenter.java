package com.hxb.reading.room.mvp.presenter;

import com.hxb.reading.room.mvp.view.iview.IMainView;
import com.hxb.reading.room.mvp.model.imodel.IMainModel;
import com.hxb.reading.room.mvp.presenter.base.BasePresenter;

public class MainPresenter extends BasePresenter<IMainView, IMainModel> {

    public MainPresenter(IMainView iView, IMainModel iModel) {
        super(iView, iModel);
    }


}
