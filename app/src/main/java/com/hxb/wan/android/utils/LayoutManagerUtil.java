package com.hxb.wan.android.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.hxb.wan.android.mvp.view.weight.CustomMoreFooter;
import com.hxb.wan.android.mvp.view.weight.CustomRefreshHeader;
import com.zhouyou.recyclerview.XRecyclerView;

/**
 * @Author :hexingbo
 * @Date :2019/4/10
 * @FileName： LayoutManagerUtil
 * @Describe :
 */
public class LayoutManagerUtil {

    /**
     * 线性水平布局
     *
     * @param context
     * @return
     */
    public static LinearLayoutManager getManager_VERTICAL(Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    /**
     * 线性垂直布局
     *
     * @param context
     * @return
     */
    public static LinearLayoutManager getManager_HORIZONTAL(Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }

    /**
     * 宫格布局
     *
     * @param context
     * @param spanCount
     * @return
     */
    public static GridLayoutManager getManager_Grid(Context context, int spanCount) {
        return new GridLayoutManager(context, spanCount);
    }

    /**
     * 瀑布流布局
     *
     * @param spanCount
     * @return
     */
    public static StaggeredGridLayoutManager getManager_StaggeredGrid(int spanCount) {
        return new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
    }

    /**
     * 宫格布吉
     *
     * @param view
     * @param staggeredGrid ture:瀑布流布局；false：普通宫格布局
     * @param spanCount
     * @param listener
     * @return
     */
    public static XRecyclerView initView(@Nullable XRecyclerView view, boolean staggeredGrid, int spanCount, XRecyclerView.LoadingListener listener) {
        view.setLayoutManager(staggeredGrid ? getManager_StaggeredGrid(spanCount) : getManager_Grid(view.getContext(), spanCount));
        view.setRefreshHeader(new CustomRefreshHeader(view.getContext()));
        view.setLoadingMoreFooter(new CustomMoreFooter(view.getContext()));
        view.setLoadingMoreEnabled(false);
        if (listener != null)
            view.setLoadingListener(listener);

        return view;
    }

    /**
     * 线性布局
     *
     * @param view
     * @param vertical true：垂直布局；false：水平布局
     * @param listener
     * @return
     */
    public static XRecyclerView initView(@Nullable XRecyclerView view, boolean vertical, XRecyclerView.LoadingListener listener) {
        view.setLayoutManager(vertical ? getManager_VERTICAL(view.getContext()) : getManager_HORIZONTAL(view.getContext()));
        view.setRefreshHeader(new CustomRefreshHeader(view.getContext()));
        view.setLoadingMoreFooter(new CustomMoreFooter(view.getContext()));
        view.setLoadingMoreEnabled(false);
        if (listener != null)
            view.setLoadingListener(listener);

        return view;
    }

    public static XRecyclerView initView(@Nullable XRecyclerView view, @Nullable RecyclerView.LayoutManager layout, XRecyclerView.LoadingListener listener) {
        view.setLayoutManager(layout);
        view.setRefreshHeader(new CustomRefreshHeader(view.getContext()));
        view.setLoadingMoreFooter(new CustomMoreFooter(view.getContext()));
        view.setLoadingMoreEnabled(false);
        if (listener != null)
            view.setLoadingListener(listener);

        return view;
    }
}
