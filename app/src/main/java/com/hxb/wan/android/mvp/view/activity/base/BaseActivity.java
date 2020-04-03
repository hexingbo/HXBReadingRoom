package com.hxb.wan.android.mvp.view.activity.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.hxb.wan.android.R;
import com.hxb.wan.android.utils.statusbar.StatusBarUtil;
import com.ljy.devring.base.activity.ActivityLifeCallback;
import com.ljy.devring.base.activity.IBaseActivity;
import com.hxb.wan.android.mvp.presenter.base.BasePresenter;
import com.ljy.devring.util.ColorBar;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author:  ljy
 * date：    2018/3/19
 * description: Activity的基类
 *
 * <a>https://www.jianshu.com/p/3d9ee98a9570</a>
 * 此基类的作用：
 * 1.重写onCreate并提供getContentLayout()、initView()等方法让子类实现。
 * 2.设置状态栏导航栏颜色。
 * 3.销毁Presenter层对View层的引用。
 * 4.实现IBaseActivity接口，以便通过Application.ActivityLifecycleCallbacks完成部分"基类操作"。
 * <p>
 * <p>
 * 由于Java的单继承的限制，DevRing库就不提供基类了，而是把一些基类操作通过Application.ActivityLifecycleCallbacks来完成。
 * 只需你的Activity实现IBaseActivity接口，并在Application中调用DevRing.init(this);
 * 即可完成以下"基类操作"：(具体请查看 {@link ActivityLifeCallback}）
 * 1.操作PublishSubject以便控制网络请求的生命周期
 * 2.根据isUseEventBus()来决定EventBus的注册/注销
 * 3.Activity栈管理的入栈与出栈
 * 4.根据isUseFragment()来决定是否注册FragmentLifecycleCallbacks
 * <p>
 * <p>
 * 这种基类实现方式，参考自JessYan <a>https://www.jianshu.com/p/75a5c24174b2</a>
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseActivity {

    @BindColor(R.color.colorPrimary)
    protected int mColor;
    @Inject
    @Nullable
    protected P mPresenter;
    protected Unbinder bind;

    protected abstract int getContentLayout();//返回页面布局id

    protected abstract void initView(Bundle savedInstanceState);//做视图相关的初始化工作

    protected abstract void initData(Bundle savedInstanceState);//做数据相关的初始化工作

    protected abstract void initEvent();//做监听事件相关的初始化工作

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentLayout() != 0) {
            setContentView(getContentLayout());
            Unbinder bind = ButterKnife.bind(this);
        }
        initBarColor();//初始化状态栏/导航栏颜色，需在设置了布局后再调用
        initView(savedInstanceState);//由具体的activity实现，做视图相关的初始化
        initData(savedInstanceState);//由具体的activity实现，做数据的初始化
        initEvent();//由具体的activity实现，做事件监听的初始化
    }

    //初始化状态栏/导航栏颜色，需在设置了布局后再调用
    private void initBarColor() {
        ViewGroup parent = findViewById(android.R.id.content);
        if (parent.getChildAt(0) instanceof DrawerLayout) {
            ColorBar.newDrawerBuilder()
                    .applyNav(true)
                    .navColor(mColor)
                    .navDepth(0)
                    .statusColor(mColor)
                    .statusDepth(0)
                    .build(this)
                    .apply();
        } else {
            ColorBar.newColorBuilder()
                    .applyNav(true)
                    .navColor(mColor)
                    .navDepth(0)
                    .statusColor(mColor)
                    .statusDepth(0)
                    .build(this)
                    .apply();
        }
        setTranspStatusBar(null);
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    public boolean isUseFragment() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }

        if (bind != null) {
            bind.unbind();
        }
    }

    /**
     * 设置沉浸式
     */
    public int setTranspStatusBar(View titleBar) {
        int statusBarHeight = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //修改状态栏颜色和文字图标颜色
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
            if (titleBar != null) {
                ViewGroup.LayoutParams params = titleBar.getLayoutParams();
                params.height += statusBarHeight;
                titleBar.setLayoutParams(params);
                titleBar.setPadding(0, statusBarHeight, 0, 0);
            }
            initStatusBar();
        }
        return statusBarHeight;
    }

    /**
     * 初始化状态栏相关，
     * PS: 设置全屏需要在调用super.onCreate(arg0);之前设置setIsFullScreen(true);否则在Android 6.0下非全屏的activity会出错;
     * SDK19：可以设置状态栏透明，但是半透明的SYSTEM_BAR_BACKGROUNDS会不好看；
     * SDK21：可以设置状态栏颜色，并且可以清除SYSTEM_BAR_BACKGROUNDS，但是不能设置状态栏字体颜色（默认的白色字体在浅色背景下看不清楚）；
     * SDK23：可以设置状态栏为浅色（SYSTEM_UI_FLAG_LIGHT_STATUS_BAR），字体就回反转为黑色。
     * 为兼容目前效果，仅在SDK23才显示沉浸式。
     */
    private void initStatusBar() {
        Window win = getWindow();

        //KITKAT也能满足，只是SYSTEM_UI_FLAG_LIGHT_STATUS_BAR（状态栏字体颜色反转）只有在6.0才有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
            // 状态栏字体设置为深色，SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 为SDK23增加
            win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            // 部分机型的statusbar会有半透明的黑色背景
            win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            win.setStatusBarColor(Color.TRANSPARENT);// SDK21

            StatusBarUtil.setStatusTextColor(true, this);

        }
    }
    

}
