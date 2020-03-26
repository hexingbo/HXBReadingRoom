package com.hxb.wan.android.mvp.view.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.yanzhenjie.sofia.StatusView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author :hexingbo
 * @Date :2020/3/23
 * @FileName： TitleBarActivity
 * @Describe :
 */
public abstract class TitleBarActivity<P extends BasePresenter> extends BaseActivity {

    @BindView(R.id.status_views)
    StatusView statusViews;
    @BindView(R.id.public_toolbar_back)
    RelativeLayout publicToolbarBack;
    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;
    @BindView(R.id.public_toolbar_text_rigth)
    TextView publicToolbarTextRigth;
    @BindView(R.id.public_toolbar)
    Toolbar publicToolbar;
    @BindView(R.id.fl_root)
    FrameLayout flRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranspStatusBar(null);
        setContentView(R.layout.public_activity_titlebar);
        bind = ButterKnife.bind(this);
        if (getContentLayout() != 0) {
            flRoot.addView(View.inflate(this, getContentLayout(), null));
        }
        bind = ButterKnife.bind(this);

        initView(savedInstanceState);//由具体的activity实现，做视图相关的初始化
        initData(savedInstanceState);//由具体的activity实现，做数据的初始化
        initEvent();//由具体的activity实现，做事件监听的初始化
    }

    public void setBarBackVisibility(boolean show) {
        publicToolbarBack.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public void setBarRightText(int res) {
        publicToolbarTextRigth.setText(res);
    }

    public void setBarRightText(String res) {
        publicToolbarTextRigth.setText(res);
    }


    @OnClick(R.id.public_toolbar_back)
    public void onPublicToolbarBackClicked() {
        finish();
    }

    @OnClick(R.id.public_toolbar_text_rigth)
    public void onPublicToolbarTextRigthClicked() {
        
    }
}
