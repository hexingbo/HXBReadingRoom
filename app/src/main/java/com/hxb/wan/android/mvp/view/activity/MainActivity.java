package com.hxb.wan.android.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.activity.DaggerMainActivityComponent;
import com.hxb.wan.android.di.module.activity.MainActivityModule;
import com.hxb.wan.android.mvp.presenter.MainPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.fragment.base.BaseFragment;
import com.hxb.wan.android.mvp.view.iview.IMainView;
import com.ljy.devring.DevRing;
import com.ljy.devring.image.support.LoadOption;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.base_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nv_menu)
    NavigationView mNavigationView;
    @BindView(R.id.base_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tl_home)
    TabLayout mTlHome;//顶部选项卡

    @BindString(R.string.app_name)
    String mStrTitle;
    @BindString(R.string.new_article)
    String mStrNewArticle;
    @BindString(R.string.new_project)
    String mStrNewProject;

    @BindColor(R.color.colorLightBlack)
    int mColorBlack;
    @BindColor(R.color.colorPrimary)
    int mColorPrimary;

    private ImageView mIvAvatar;
    private BaseFragment mCurrentFragment;//当前展示的Fragment
    private long mExitTime;
    private int mCurrentIndex;//记录当前显示的fragment索引，用于配置变化重建后恢复页面
    
    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle bundle) {
        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build().inject(this);

        //如果调用了setSupportActionBar，那就必须在setSupportActionBar之前将标题置为空字符串，否则设置具体标题会无效
        mToolbar.setTitle("");
        this.setSupportActionBar(mToolbar);
        mToolbar.setTitle(mStrTitle);

        //初始化TabLayout
        mTlHome.setTabMode(TabLayout.MODE_FIXED);//支持水平滑动，当屏幕空间不足
        mTlHome.setTabTextColors(mColorBlack, mColorPrimary);//设置文本在未选中和选中时候的颜色
        mTlHome.setSelectedTabIndicatorColor(mColorPrimary);//设置选中长条的颜色
        mTlHome.addTab(mTlHome.newTab().setText(mStrNewArticle), mCurrentIndex == 0 ? true : false);
        mTlHome.addTab(mTlHome.newTab().setText(mStrNewProject), mCurrentIndex == 1 ? true : false);

        //侧滑抽屉里的圆形头像，比较特殊，无法通过butterknife初始化
        mIvAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.iv_avatar);
        DevRing.imageManager().loadRes(R.mipmap.ic_launcher, mIvAvatar, new LoadOption().setIsCircle(true));
        
    }

    @Override
    protected void initData(Bundle bundle) {
        
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void showLoading() {
        
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        
    }
}
