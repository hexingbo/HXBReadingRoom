package com.hxb.wan.android.mvp.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.hxb.wan.android.R;
import com.hxb.wan.android.mvp.model.entity.res.WxArticleDataBean;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import java.util.List;

/**
 * @Author :hexingbo
 * @Date :2020/4/2
 * @FileName： NewArticleAdapter
 * @Describe :
 */
public class NewArticleAdapter extends HelperStateRecyclerViewAdapter<WxArticleDataBean> {

    public NewArticleAdapter(List<WxArticleDataBean> data, Context context) {
        super(data, context, R.layout.item_new_article);
    }

    @Override
    public View getEmptyView(ViewGroup parent) {
        return mLInflater.inflate(R.layout.view_state_empty, parent, false);
    }

    @Override
    public View getErrorView(ViewGroup parent) {
        return mLInflater.inflate(R.layout.view_state_error, parent, false);
    }

    @Override
    public View getLoadingView(ViewGroup parent) {
        return mLInflater.inflate(R.layout.view_state_loading, parent, false);
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, WxArticleDataBean item) {
        viewHolder.setText(R.id.tv_title, item.getTitle());
        viewHolder.setText(R.id.tv_author, TextUtils.isEmpty(item.getAuthor()) ? "" : "作者：" + item.getAuthor());
        viewHolder.setVisible(R.id.tv_author, !TextUtils.isEmpty(item.getAuthor()));
        viewHolder.setText(R.id.tv_shareUser, TextUtils.isEmpty(item.getShareUser()) ? "" : "分享人：" + item.getShareUser());
        viewHolder.setVisible(R.id.tv_shareUser, !TextUtils.isEmpty(item.getShareUser()));
        String category = TextUtils.isEmpty(item.getSuperChapterName()) ? (TextUtils.isEmpty(item.getChapterName()) ? "" : item.getChapterName()) : item.getSuperChapterName() + ((TextUtils.isEmpty(item.getChapterName()) ? "" : "/" + item.getChapterName()));
        viewHolder.setText(R.id.tv_category, TextUtils.isEmpty(category) ? "" : "分类：" + category);
        viewHolder.setVisible(R.id.tv_category, !TextUtils.isEmpty(category));
        viewHolder.setText(R.id.tv_time, TextUtils.isEmpty(item.getNiceDate()) ? "" : "时间：" + item.getNiceDate());
        viewHolder.setVisible(R.id.tv_time, !TextUtils.isEmpty(item.getNiceDate()));
        viewHolder.setImageResource(R.id.img_shoucang, R.mipmap.ic_shoucang_n);
    }
}
