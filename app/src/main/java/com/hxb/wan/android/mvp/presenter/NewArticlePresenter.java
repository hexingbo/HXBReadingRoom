package com.hxb.wan.android.mvp.presenter;

import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.hxb.wan.android.mvp.view.iview.INewArticleView;
import com.hxb.wan.android.mvp.model.imodel.INewArticleModel;

public class NewArticlePresenter extends BasePresenter<INewArticleView, INewArticleModel> {

    public NewArticlePresenter(INewArticleView iView, INewArticleModel iModel) {
        super(iView, iModel);
    }


}
