package com.hxb.reading.room.mvp.presenter;

import com.hxb.reading.room.mvp.presenter.base.BasePresenter;
import com.hxb.reading.room.mvp.view.iview.IStartView;
import com.hxb.reading.room.mvp.model.imodel.IStartModel;

public class StartPresenter extends BasePresenter<IStartView, IStartModel> {

    public StartPresenter(IStartView iView, IStartModel iModel) {
        super(iView, iModel);
    }


}
