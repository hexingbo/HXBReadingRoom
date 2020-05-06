package com.hxb.wan.android.mvp.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxb.wan.android.R;
import com.hxb.wan.android.di.component.activity.DaggerMainActivityComponent;
import com.hxb.wan.android.di.module.activity.MainActivityModule;
import com.hxb.wan.android.mvp.model.entity.event.MainDataEvent;
import com.hxb.wan.android.mvp.model.entity.res.BannerData;
import com.hxb.wan.android.mvp.presenter.MainPresenter;
import com.hxb.wan.android.mvp.view.activity.base.BaseActivity;
import com.hxb.wan.android.mvp.view.fragment.NewArticleFragment;
import com.hxb.wan.android.mvp.view.fragment.NewProjectFragment;
import com.hxb.wan.android.mvp.view.fragment.base.BaseFragment;
import com.hxb.wan.android.mvp.view.iview.IMainView;
import com.hxb.wan.android.utils.GlideImageLoader;
import com.ljy.devring.DevRing;
import com.ljy.devring.image.support.LoadOption;
import com.ljy.devring.other.RingLog;
import com.ljy.devring.util.AppManagerUtil;
import com.ljy.devring.util.RingToast;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @Inject
    Dialog mDialog;

    @BindView(R.id.base_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nv_menu)
    NavigationView mNavigationView;
    @BindView(R.id.base_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tl_home)
    TabLayout mTlHome;//顶部选项卡
    @BindView(R.id.head_banner)
    Banner mBanner;

    @BindString(R.string.app_name)
    String mStrTitle;
    @BindString(R.string.new_article)
    String mStrNewArticle;
    @BindString(R.string.new_project)
    String mStrNewProject;
    @BindString(R.string.exit_confirm)
    String mStrExitConfirm;

    @BindColor(R.color.colorLightBlack)
    int mColorBlack;
    @BindColor(R.color.colorPrimary)
    int mColorPrimary;


    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;

    private ImageView mIvAvatar;
    private TextView mTvUserName;

    @Inject
    @Named("NewArticle")
    NewArticleFragment mNewArticleFragment;//最新文字的fragment
    @Inject
    @Named("NewProject")
    NewProjectFragment mNewProjectFragment;//最新项目的fragment

    private BaseFragment mCurrentFragment;//当前展示的Fragment
    private int mCurrentIndex;//记录当前显示的fragment索引，用于配置变化重建后恢复页面

    private long mExitTime;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index", mCurrentIndex);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        DaggerMainActivityComponent.builder().mainActivityModule(new MainActivityModule(this)).build().inject(this);

        //如果经过了配置变化而重建(如横竖屏切换)，则不使用新建的Fragment。
        if (savedInstanceState != null) {
            NewArticleFragment oldPlayingFragment = (NewArticleFragment) getSupportFragmentManager().findFragmentByTag("NewArticle");
            if (oldPlayingFragment != null) {
                mNewArticleFragment = oldPlayingFragment;
            }
            NewProjectFragment oldCommingFragment = (NewProjectFragment) getSupportFragmentManager().findFragmentByTag("NewProject");
            if (oldCommingFragment != null) {
                mNewProjectFragment = oldCommingFragment;
            }
            mCurrentIndex = savedInstanceState.getInt("index");
        }

        //如果调用了setSupportActionBar，那就必须在setSupportActionBar之前将标题置为空字符串，否则设置具体标题会无效
        mToolbar.setTitle("");
        this.setSupportActionBar(mToolbar);
        mToolbar.setTitle(mStrTitle);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        //初始化TabLayout
        mTlHome.setTabMode(TabLayout.MODE_FIXED);//支持水平滑动，当屏幕空间不足
        mTlHome.setTabTextColors(mColorBlack, mColorPrimary);//设置文本在未选中和选中时候的颜色
        mTlHome.setSelectedTabIndicatorColor(mColorPrimary);//设置选中长条的颜色
        mTlHome.addTab(mTlHome.newTab().setText(mStrNewArticle), mCurrentIndex == 0 ? true : false);
        mTlHome.addTab(mTlHome.newTab().setText(mStrNewProject), mCurrentIndex == 1 ? true : false);

        //解决Android使用Menu时不显示彩色图标的问题
        mNavigationView.setItemIconTintList(null);
        //侧滑抽屉里的圆形头像，比较特殊，无法通过butterknife初始化
        mIvAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.iv_avatar);
        mTvUserName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_userNmae);
    }

    @Override
    protected void initData(Bundle bundle) {
        switch (mCurrentIndex) {
            case 0:
                setDefaultFragment(mNewArticleFragment, "NewArticle");//设置默认的Fragment
                break;
            case 1:
                setDefaultFragment(mNewProjectFragment, "NewProject");//设置默认的Fragment
                break;
        }

        setUserInfo();
        mPresenter.getBannerList();
    }

    @Override
    public void getBannerListSuccess(List<BannerData> bannerDataList) {
        mBannerTitleList = new ArrayList<>();
        List<String> bannerImageList = new ArrayList<>();
        mBannerUrlList = new ArrayList<>();
        for (BannerData bannerData : bannerDataList) {
            mBannerTitleList.add(bannerData.getTitle());
            bannerImageList.add(bannerData.getImagePath());
            mBannerUrlList.add(bannerData.getUrl());
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(bannerImageList);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(mBannerTitleList);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(bannerDataList.size() * 400);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
//        mBanner.start();
    }

    @Override
    public Activity getActivity() {
        return this;
    }


    /**
     * 设置用户信息
     */
    private void setUserInfo() {
        String userHead = MainDataEvent.init().getUserHead();
        if (TextUtils.isEmpty(userHead)) {
            DevRing.imageManager().loadRes(R.mipmap.ic_launcher, mIvAvatar, new LoadOption().setIsCircle(true));
        } else {
            DevRing.imageManager().loadNet(userHead, mIvAvatar, new LoadOption().setIsCircle(true));
        }

        boolean login = MainDataEvent.init().getLogin();
        mTvUserName.setText(login ? (TextUtils.isEmpty(MainDataEvent.init().getUserName()) ? getString(R.string.str_nick_name) : MainDataEvent.init().getUserName()) : getString(R.string.str_login_no));

        mTvUserName.setEnabled(!login);
        mTvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录
                AppManagerUtil.jump(login ? UserLoginActivity.class : UserLoginActivity.class);
            }
        });
        mNavigationView.getMenu().findItem(R.id.nav_item_collect).setTitle(getResources().getString(R.string.strShoucangjia, MainDataEvent.init().getUserCollectedNumber()));
        mNavigationView.getMenu().findItem(R.id.nav_item_login_out).setVisible(login);

    }

    @Override
    protected void initEvent() {
        setTabMenuListener();
        setNavigationListener();
        setDrawerLayoutListener();
    }

    ////设置DrawerLayout滑动的相关监听
    private void setDrawerLayoutListener() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                mBanner.stopAutoPlay();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                mBanner.startAutoPlay();
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                mContent.setTranslationX(drawerView.getMeasuredWidth() * slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    //侧边栏点击事件
    private void setNavigationListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_item_collect:
                        //收藏夹
                        AppManagerUtil.jump(MyCollectedManageActivity.class);
                        break;
                    case R.id.nav_item_guangchang:
                        //干货广场
                        break;
                    case R.id.nav_item_daohang:
                        //导航菜单
                        RingLog.e("menuvalue："+menuItem.getTitle());
                        AppManagerUtil.jump(NavigateMenuActivity.class,NavigateMenuActivity.strBundle,menuItem.getTitle()+"");
                        break;
                    case R.id.nav_item_xiangmu:
                        //项目大厅
                        AppManagerUtil.jump(ProjectHallActivity.class);
                        break;
                    case R.id.nav_item_tixi:
                        //知识体系
                        AppManagerUtil.jump(KnowledgeSystemActivity.class);
                        break;
                    case R.id.nav_item_gongjulan:
                        //常用工具
                        break;
                    case R.id.nav_item_gongzhonghao:
                        //公众号
                        AppManagerUtil.jump(PublicAccountActivity.class);
                        break;
                    case R.id.nav_item_wenda:
                        //问答交流
                        break;
                    case R.id.nav_item_about_us:
                        //关于我们
                        AppManagerUtil.jump(AboutUsActivity.class);
                        break;
                    case R.id.nav_item_login_out:
                        //退出登录
                        mPresenter.goLogOutUser();
                        break;
                }
                //收起侧边栏
//                mDrawerLayout.closeDrawers();

                return false;//true则点击的菜单项会变成选中状态,，false则不会变成选中状态
            }
        });

        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMessage("头像被点击了");
            }
        });
    }

    //菜单点击切换
    private void setTabMenuListener() {
        mTlHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mCurrentIndex = 0;
                        clickNewArticle();
                        break;

                    case 1:
                        mCurrentIndex = 1;
                        clickNewProject();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        RingToast.show(message);
    }

    //设置默认Fragment
    private void setDefaultFragment(BaseFragment fragment, String tag) {
        if (!fragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_home, fragment, tag).commit();
        }
        mCurrentFragment = fragment;
        mCurrentFragment.setUserVisibleHint(true);
    }

    //显示“最新文章”Fragment
    private void clickNewArticle() {
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), mNewArticleFragment, "NewArticle");
    }

    //显示“最新项目”Fragment
    private void clickNewProject() {
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), mNewProjectFragment, "NewProject");
    }

    //显示或隐藏Fragment，用于切换Fragment的展示
    private void addOrShowFragment(FragmentTransaction transaction, BaseFragment fragment, String tag) {
        if (mCurrentFragment == fragment) return;

        if (!fragment.isAdded()) {
            transaction.hide(mCurrentFragment).add(R.id.fl_home, fragment, tag).commit();
        } else {
            transaction.hide(mCurrentFragment).show(fragment).commit();
        }

        //不与ViewPager嵌套的话，需要显式调用setUserVisibleHint才能使用懒加载功能。
        mCurrentFragment.setUserVisibleHint(false);
        mCurrentFragment = fragment;
        mCurrentFragment.setUserVisibleHint(true);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK://返回键
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    //侧边栏展开则收起
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else if (System.currentTimeMillis() - mExitTime > 2000) {
                    //两次点击返回键大于2s则提示
                    showMessage(mStrExitConfirm);
                    mExitTime = System.currentTimeMillis();
                } else {
                    //退出程序
                    DevRing.activityListManager().exitApp();
                }
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    //接收事件总线发来的事件
    @org.greenrobot.eventbus.Subscribe //如果使用默认的EventBus则使用此@Subscribe
    @com.hxb.wan.android.mvp.model.bus.support.Subscribe //如果使用RxBus则使用此@Subscribe
    public void handleEvent(MainDataEvent event) {
        if (!event.getLogin()) {
            //收起侧边栏
            mDrawerLayout.closeDrawers();
        }
        //更新侧滑栏中菜单项的用户信息
        setUserInfo();//更新用户信息

        if (mNewArticleFragment.getRecyclerView() != null)
            mNewArticleFragment.getRecyclerView().refresh();
        if (mNewProjectFragment.getRecyclerView() != null)
            mNewProjectFragment.getRecyclerView().refresh();
    }

}
