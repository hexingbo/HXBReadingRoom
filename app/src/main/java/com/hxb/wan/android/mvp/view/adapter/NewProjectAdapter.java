package com.hxb.wan.android.mvp.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.hxb.wan.android.R;
import com.hxb.wan.android.mvp.model.entity.res.WxProjectDataBean;
import com.ljy.devring.DevRing;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;
import com.zhouyou.recyclerview.adapter.HelperStateRecyclerViewAdapter;

import java.util.List;

/**
 * @Author :hexingbo
 * @Date :2020/4/2
 * @FileName： NewArticleAdapter
 * @Describe :
 */
public class NewProjectAdapter extends HelperStateRecyclerViewAdapter<WxProjectDataBean> {

    private OnItemClickListener<WxProjectDataBean> listener;

    public NewProjectAdapter(List<WxProjectDataBean> data, Context context) {
        super(data, context, R.layout.item_new_project);
    }

    @Override
    public View getEmptyView(ViewGroup parent) {
        return mLInflater.inflate(R.layout.view_state_empty, parent, false);
    }

    @Override
    public View getErrorView(ViewGroup parent) {
        View view = mLInflater.inflate(R.layout.view_state_error, parent, false);
        view.findViewById(R.id.ll_error_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, null, -1);
                }
            }
        });
        return view;
    }

    @Override
    public View getLoadingView(ViewGroup parent) {
        return mLInflater.inflate(R.layout.view_state_loading, parent, false);
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, WxProjectDataBean item) {
        viewHolder.setText(R.id.tv_title, item.getDesc());
        viewHolder.setText(R.id.tv_author, TextUtils.isEmpty(item.getAuthor()) ? "" : "作者：" + item.getAuthor());
        viewHolder.setVisible(R.id.tv_author, !TextUtils.isEmpty(item.getAuthor()));
        viewHolder.setText(R.id.tv_shareUser, TextUtils.isEmpty(item.getShareUser()) ? "" : "分享人：" + item.getShareUser());
        viewHolder.setVisible(R.id.tv_shareUser, !TextUtils.isEmpty(item.getShareUser()));
        viewHolder.setText(R.id.tv_category, TextUtils.isEmpty(item.getChapterName()) ? "" : "分类：" + item.getChapterName());
        viewHolder.setVisible(R.id.tv_category, !TextUtils.isEmpty(item.getChapterName()));
        viewHolder.setText(R.id.tv_time, TextUtils.isEmpty(item.getNiceDate()) ? "" : "时间：" + item.getNiceDate());
        viewHolder.setVisible(R.id.tv_time, !TextUtils.isEmpty(item.getNiceDate()));
        viewHolder.getView(R.id.img_shoucang).setSelected(item.isCollect());
//        viewHolder.setImageResource(R.id.img_shoucang, R.mipmap.ic_shoucang_n);
        viewHolder.getView(R.id.img_shoucang).setSelected(item.isCollect());
//        viewHolder.setImageUrl(R.id.img_envelopePic,item.getEnvelopePic());
        DevRing.imageManager().loadNet(item.getEnvelopePic(), viewHolder.getView(R.id.img_envelopePic));
        viewHolder.setOnClickListener(R.id.img_shoucang, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, item, position);
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, item, position);
                }
            }
        });
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.listener = onItemClickListener;
    }
}
