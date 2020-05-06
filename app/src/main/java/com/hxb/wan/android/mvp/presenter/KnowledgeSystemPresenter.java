package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.IKnowledgeSystemView;
import com.hxb.wan.android.mvp.model.imodel.IKnowledgeSystemModel;

public class KnowledgeSystemPresenter extends BasePresenter<IKnowledgeSystemView, IKnowledgeSystemModel> {

    public KnowledgeSystemPresenter(IKnowledgeSystemView iView, IKnowledgeSystemModel iModel) {
        super(iView, iModel);
    }


}
